package org.artdevs.meetingslog.core.dao.impl;

import org.artdevs.meetingslog.core.dao.MessageDAO;
import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Slava on 13.01.2015.
 */

@Component
public class MessageSqlDao implements MessageDAO{

    @Autowired
    NamedParameterJdbcTemplate namedParamTemplate;

    private RowMapper<Message> msgRowMapper= new RowMapper<Message>() {
        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Message(
                    rs.getInt("msg_id"),
                    rs.getBoolean("msg_readonly"),
                    rs.getString("msg_text")
            );
        }
    };

    @Override
    public List<Message> getAll() {
        final String qryStr="SELECT * FROM ml_user_messages";
        return namedParamTemplate.query(qryStr, msgRowMapper);
    }

    @Override
    public List<Message> getByUser(User user) {
        final String qryStr="SELECT * FROM ml_user_messages WHERE owner_id=:owner_id";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("owner_id",user.getId());

        return namedParamTemplate.query(qryStr,mapPars, msgRowMapper);
    }

    @Override
    public Message findById(int id) {
        final String qryStr="SELECT * FROM ml_messages WHERE id=:id";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("id",id);

        try {
            return namedParamTemplate.queryForObject(qryStr, mapPars, msgRowMapper);
        }catch (Exception e){return null;}
    }

    @Override
    public void insert(Message message) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("INSERT INTO ml_message ");
        qryStrBuilder.append("(readonly,msg_text)");
        qryStrBuilder.append(" VALUES ");
        qryStrBuilder.append("(:readonly,:msg_text)");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("readonly", message.getReadonly());
        mapPars.put("msg_text", message.getMsg_text());

        KeyHolder lastId=new GeneratedKeyHolder();
        SqlParameterSource pars=new MapSqlParameterSource(mapPars);

        namedParamTemplate.update(qryStr, pars,lastId);
        message.setId(lastId.getKey().intValue());
    }

    @Override
    public void updateById(Message message) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_message SET ");
        qryStrBuilder.append("readonly=:readonly,msg_text=:msg_text");
        qryStrBuilder.append(" WHERE id=:id ");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",message.getId());
        mapPars.put("readonly", message.getReadonly());
        mapPars.put("msg_text", message.getMsg_text());

        namedParamTemplate.update(qryStr, mapPars);
    }

    @Override
    public void removeById(int id) {

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",id);

        final String qryStr="DELETE FROM ml_mesages WHERE id=:id";

        namedParamTemplate.update(qryStr,mapPars);

    }

    @Override
    public void removeByUser(User user) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("owner_id",user.getId());

        final String qryStr="DELETE FROM ml_mesages WHERE owner_id=:owner_id";

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void toggleReadonly(Message message, boolean status) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_message SET ");
        qryStrBuilder.append("readonly=:readonly");
        qryStrBuilder.append(" WHERE id=:id ");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",message.getId());
        mapPars.put("readonly", status);

        namedParamTemplate.update(qryStr, mapPars);
    }
}
