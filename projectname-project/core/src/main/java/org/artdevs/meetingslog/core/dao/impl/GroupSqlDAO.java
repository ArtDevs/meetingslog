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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Slava on 24.01.2015.
 */
@Component
public class GroupSqlDAO implements GroupDAO {

    @Autowired
    NamedParameterJdbcTemplate namedParamTemplate;

    @Autowired
    UserSqlDao userSqlDao;

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
    public List<Group> getByOwner(User user) {
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

        try {
            return namedParamTemplate.queryForObject(qryStr, params, groupRowMapper);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void insert(Group group) {
        final String qryStr="INSERT INTO ml_groups (name,owner_id) VALUES (:name,:owner_id)";

        Map<String,Object> params= new HashMap<>();
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
        mapPars.put("owner_id", group.getOwner_id());

        namedParamTemplate.update(qryStr, mapPars);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeById(int id) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",id);

        String  qryStr="DELETE FROM ml_user_group_ref WHERE group_id=:id";
        namedParamTemplate.update(qryStr,mapPars);

        qryStr="DELETE FROM ml_groups WHERE id=:id";
        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeByOwner(User user) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        ArrayList<Group> removedGroups= (ArrayList<Group>) getByOwner(user);

        String qryStr="DELETE FROM ml_user_group_ref WHERE group_id=:id";

        for(Group group:removedGroups){
            mapPars.put("id",group.getId());
            namedParamTemplate.update(qryStr,mapPars);
        }

        mapPars.put("owner_id",user.getId());

        qryStr="DELETE FROM ml_groups WHERE owner_id=:owner_id";

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Group createGroup(String name, List<User> userList) {
        if(userList.size()>0) {
            Group group = new Group();

            group.setName(name);
            group.setOwner_id(userList.get(0).getId());
            insert(group);

            for (User user : userList) {
                addUser(group,user);
            }
            return group;
        }
        else
            return null;
    }

    @Override
    public void changeOwner(Group group, User newOwner) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("owner_id",newOwner.getId());
        mapPars.put("id",group.getId());

        final String  qryStr="UPDATE ml_groups SET owner_id=:owner_id WHERE id=:id";
        namedParamTemplate.update(qryStr,mapPars);

    }

    @Override
    public void addUser(Group group, User user) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("group_id",group.getId());
        mapPars.put("user_id",user.getId());

        String  qryStr="SELECT COUNT(id) FROM ml_user_group_ref WHERE group_id=:group_id AND user_id=:user_id";
        Integer count=namedParamTemplate.queryForObject(qryStr,mapPars,Integer.class);

        if(count==0) {
            qryStr = "INSERT INTO ml_user_group_ref SET group_id=:group_id, user_id=:user_id";
            namedParamTemplate.update(qryStr, mapPars);
        }
    }

    @Override
    public void removeUser(Group group, User user) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("group_id",group.getId());
        mapPars.put("user_id",user.getId());

        String  qryStr="DELETE FROM ml_user_group_ref WHERE group_id=:group_id AND user_id=:user_id";
        namedParamTemplate.update(qryStr, mapPars);
    }

    @Override
    public List<Group> getByUser(User user) {
        final String qryStr="SELECT * FROM ml_groups WHERE id IN " +
                "(SELECT group_id FROM ml_user_group_ref WHERE user_id=:user_id)";
        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("user_id",user.getId());

        return namedParamTemplate.query(qryStr,mapPars,groupRowMapper);
    }

    @Override
    public List<User> getUsers(Group group) {
        final String qryStr="SELECT * FROM ml_users WHERE id IN " +
                "(SELECT user_id FROM ml_user_group_ref WHERE group_id=:group_id)";
        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("group_id",group.getId());

        return namedParamTemplate.query(qryStr,mapPars,userSqlDao.userRowMapper);
    }
}
