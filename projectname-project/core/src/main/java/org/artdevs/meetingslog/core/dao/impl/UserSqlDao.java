package org.artdevs.meetingslog.core.dao.impl;

import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.Role;
import org.artdevs.meetingslog.core.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Slava on 11.12.2014.
 */

@Component
public class UserSqlDao implements UserDAO{

    @Autowired
    NamedParameterJdbcTemplate namedParamTemplate;

    private RowMapper<User> userRowMapper=new RowMapper<User>(){
        public User mapRow (ResultSet res,int rowNum)throws SQLException {
            return new User(
                    res.getInt("id"),
                    new Role(res.getInt("role_id"),
                             res.getString("role_name"),
                             res.getString("role_description"),
                             res.getInt("role_permissions")),
                    res.getString("login"),
                    res.getString("password"),
                    res.getString("firstName"),
                    res.getString("secondName"),
                    res.getString("email"),
                    res.getString("comment"),
                    res.getDate("tmLastLogin"),
                    res.getDate("tmRegistered")
            );
        }
    };

    @Override
    public List<User> getAll(){
        final String qryStr="SELECT * FROM ml_users_roles";

        return namedParamTemplate.query(qryStr, userRowMapper);
    }

    @Override
    public List<User> getByEmail(String email) {
        final String qryStr="SELECT * FROM ml_users_roles WHERE email=:email";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("email",email);

        return namedParamTemplate.query(qryStr,mapPars, userRowMapper);
    }

    @Override
    public User findById(int id) {
        final String qryStr="SELECT * FROM ml_users_roles WHERE id=:id";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("id",id);

        try {
            return namedParamTemplate.queryForObject(qryStr, mapPars, userRowMapper);
        }catch (Exception e){return null;}
    }

    @Override
    public User findByLogin(String login) {
        final String qryStr="SELECT * FROM ml_users_roles WHERE login=:login";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("login",login);

        try {
            return namedParamTemplate.queryForObject(qryStr, mapPars, userRowMapper);
        }catch (Exception e){return null;}
    }

    @Override
    public void insert(User user) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("INSERT INTO ml_users ");
        qryStrBuilder.append("(login,ref_role,password,firstName,secondName,email,comment,tmLastLogin,tmRegistered)");
        qryStrBuilder.append(" VALUES ");
        qryStrBuilder.append("(:login,:ref_role,:password,:firstName,:secondName,:email,:comment,:tmLastLogin,:tmRegistered)");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("login", user.getLogin());
        mapPars.put("ref_role", user.getRole().getId());
        mapPars.put("password", user.getPassword());
        mapPars.put("firstName", user.getFirstName());
        mapPars.put("secondName", user.getSecondName());
        mapPars.put("email", user.getEmail());
        mapPars.put("comment", user.getComment());
        mapPars.put("tmLastLogin", user.getTmLastLogin());
        mapPars.put("tmRegistered", user.getTmRegistered());

        namedParamTemplate.update(qryStr,mapPars);
        user.setId(findByLogin(user.getLogin()).getId());
    }

    @Override
    public void updateById(User user) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_users SET ");
        qryStrBuilder.append("login=:login,ref_role=:ref_role,password=:password,firstName=:firstName,secondName=:secondName,email=:email,");
        qryStrBuilder.append("comment=:comment,tmLastLogin=:tmLastLogin,tmRegistered=:tmRegistered");
        qryStrBuilder.append(" WHERE id=:id");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id", user.getId());
        mapPars.put("ref_role", user.getRole().getId());
        mapPars.put("login", user.getLogin());
        mapPars.put("password", user.getPassword());
        mapPars.put("firstName", user.getFirstName());
        mapPars.put("secondName", user.getSecondName());
        mapPars.put("email", user.getEmail());
        mapPars.put("comment", user.getComment());
        mapPars.put("tmLastLogin", user.getTmLastLogin());
        mapPars.put("tmRegistered", user.getTmRegistered());

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void updateByLogin(User user) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_users SET ");
        qryStrBuilder.append("id=:id,ref_role=:ref_role,password=:password,firstName=:firstName,secondName=:secondName,email=:email,");
        qryStrBuilder.append("comment=:comment,tmLastLogin=:tmLastLogin,tmRegistered=:tmRegistered");
        qryStrBuilder.append(" WHERE login=:login");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id", user.getId());
        mapPars.put("ref_role", user.getRole().getId());
        mapPars.put("login", user.getLogin());
        mapPars.put("password", user.getPassword());
        mapPars.put("firstName", user.getFirstName());
        mapPars.put("secondName", user.getSecondName());
        mapPars.put("email", user.getEmail());
        mapPars.put("comment", user.getComment());
        mapPars.put("tmLastLogin", user.getTmLastLogin());
        mapPars.put("tmRegistered", user.getTmRegistered());

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void removeById(int id) {

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",id);

        final String qryStr="DELETE FROM ml_users WHERE id=:id";

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void removeByLogin(String login) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("login",login);

        final String qryStr="DELETE FROM ml_users WHERE login=:login";

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        User usr=findByLogin(login);

        return usr.getPassword().equals(password);
    }
}
