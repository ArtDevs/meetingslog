package org.artdevs.meetingslog.core.dao.impl;

import org.artdevs.meetingslog.core.dao.GroupDAO;
import org.artdevs.meetingslog.core.model.Group;
import org.artdevs.meetingslog.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Slava on 24.01.2015.
 */
public class GroupSqlDAO implements GroupDAO {

    @Autowired
    NamedParameterJdbcTemplate namedParamTemplate;

    private RowMapper<Group> groupRowMapper=new RowMapper<Group>() {
        @Override
        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Group(rs.getInt("id"),rs.getString("name"),rs.getInt("owner_id"));
        }
    };

    @Override
    public List<Group> getAll() {
        final String qryStr="SELECT * FROM ml_groups";
        return namedParamTemplate.query(qryStr,groupRowMapper);
    }

    @Override
    public List<Group> getByUser(User user) {
        final String qryStr="SELECT * FROM ml_groups WHERE owner_id=:owner_id";

        Map<String,Object> params=new HashMap <String,Object>();
        params.put("owner_id",user.getId());

        return namedParamTemplate.query(qryStr,params,groupRowMapper);
    }

    @Override
    public Group findById(int id) {
        final String qryStr="SELECT * FROM ml_groups WHERE id=:id";

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("id",id);

        return namedParamTemplate.queryForObject(qryStr, params, groupRowMapper);
    }

    @Override
    public void insert(Group group) {
        final String qryStr="INSERT INTO ml_groups (id,name,owner_id) VALUES (:id,:name,:owner_id)";

        Map<String,Object> params= new HashMap<>();
        params.put("id",group.getId());
        params.put("name",group.getName());
        params.put("owner_id",group.getOwner_id());

        KeyHolder lastId=new GeneratedKeyHolder();
        SqlParameterSource pars=new MapSqlParameterSource(params);

        namedParamTemplate.update(qryStr,pars,lastId);
        group.setId(lastId.getKey().intValue());
    }

    @Override
    public void updateById(Group group) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_groups SET ");
        qryStrBuilder.append("name=:name,owner_id=:owner_id");
        qryStrBuilder.append(" WHERE id=:id");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",group.getId());
        mapPars.put("name", group.getName());
        mapPars.put("msg_text", group.getOwner_id());

        namedParamTemplate.update(qryStr, mapPars);
    }

    @Override
    public void removeById(int id) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",id);

        final String qryStr="DELETE FROM ml_groups WHERE id=:id";

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void removeByUser(User user) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("owner_id",user.getId());

        final String qryStr="DELETE FROM ml_groups WHERE owner_id=:owner_id";

        namedParamTemplate.update(qryStr,mapPars);
    }
}
