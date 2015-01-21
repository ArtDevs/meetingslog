package org.artdevs.meetingslog.core.dao.impl;

import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.Role;
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
                    res.getString("login"),
                    res.getString("password"),
                    res.getString("firstName"),
                    res.getString("secondName"),
                    res.getString("email"),
                    res.getString("address"),
                    res.getString("phoneNumber1"),
                    res.getString("phoneNumber2"),
                    res.getString("comment"),
                    res.getDate("tmLastLogin"),
                    res.getDate("tmRegistered")
            );
        }
    };

    private RowMapper<Role> roleRowMapper= new RowMapper<Role>() {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Role(
                rs.getInt("role_id"),
                rs.getString("role_name"),
                rs.getString("role_description"),
                rs.getInt("permissions")
            );
        }
    };

    @Override
    public List<User> getAll(){
        final String qryStr="SELECT * FROM ml_users";

        return namedParamTemplate.query(qryStr, userRowMapper);
    }

    @Override
    public List<User> getByEmail(String email) {
        final String qryStr="SELECT * FROM ml_users WHERE email=:email";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("email",email);

        return namedParamTemplate.query(qryStr,mapPars, userRowMapper);
    }

    @Override
    public User findById(int id) {
        final String qryStr="SELECT * FROM ml_users WHERE id=:id";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("id",id);

        try {
            return namedParamTemplate.queryForObject(qryStr, mapPars, userRowMapper);
        }catch (Exception e){return null;}
    }

    @Override
    public User findByLogin(String login) {
        final String qryStr="SELECT * FROM ml_users WHERE login=:login";

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
        qryStrBuilder.append("(login,password,firstName,secondName,email,address,phoneNumber1,phoneNumber2," +
                "comment,tmLastLogin,tmRegistered)");
        qryStrBuilder.append(" VALUES ");
        qryStrBuilder.append("(:login,:password,:firstName,:secondName,:email,:address,:phoneNumber1,:phoneNumber2" +
                ":comment,:tmLastLogin,:tmRegistered)");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("login", user.getLogin());
        mapPars.put("password", user.getPassword());
        mapPars.put("firstName", user.getFirstName());
        mapPars.put("secondName", user.getSecondName());
        mapPars.put("email", user.getEmail());
        mapPars.put("address", user.getEmail());
        mapPars.put("phoneNumber1", user.getEmail());
        mapPars.put("phoneNumber2", user.getEmail());
        mapPars.put("comment", user.getComment());
        mapPars.put("tmLastLogin", user.getTmLastLogin());
        mapPars.put("tmRegistered", user.getTmRegistered());

        KeyHolder lastId=new GeneratedKeyHolder();
        SqlParameterSource pars=new MapSqlParameterSource(mapPars);

        namedParamTemplate.update(qryStr, pars,lastId);
        user.setId(lastId.getKey().intValue());
    }

    @Override
    public void updateById(User user) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_users SET ");
        qryStrBuilder.append("login=:login,password=:password,firstName=:firstName,secondName=:secondName,email=:email,");
        qryStrBuilder.append("address=:address,phoneNumber1=:phoneNumber1,phoneNumber2=:phoneNumber2,");
        qryStrBuilder.append("comment=:comment,tmLastLogin=:tmLastLogin,tmRegistered=:tmRegistered");
        qryStrBuilder.append(" WHERE id=:id");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id", user.getId());
        mapPars.put("login", user.getLogin());
        mapPars.put("password", user.getPassword());
        mapPars.put("firstName", user.getFirstName());
        mapPars.put("secondName", user.getSecondName());
        mapPars.put("email", user.getEmail());
        mapPars.put("address", user.getEmail());
        mapPars.put("phoneNumber1", user.getEmail());
        mapPars.put("phoneNumber2", user.getEmail());
        mapPars.put("comment", user.getComment());
        mapPars.put("tmLastLogin", user.getTmLastLogin());
        mapPars.put("tmRegistered", user.getTmRegistered());

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void updateByLogin(User user) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE ml_users SET ");
        qryStrBuilder.append("id=:id,password=:password,firstName=:firstName,secondName=:secondName,email=:email,");
        qryStrBuilder.append("address=:address,phoneNumber1=:phoneNumber1,phoneNumber2=:phoneNumber2,");
        qryStrBuilder.append("comment=:comment,tmLastLogin=:tmLastLogin,tmRegistered=:tmRegistered");
        qryStrBuilder.append(" WHERE login=:login");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id", user.getId());
        mapPars.put("login", user.getLogin());
        mapPars.put("password", user.getPassword());
        mapPars.put("firstName", user.getFirstName());
        mapPars.put("secondName", user.getSecondName());
        mapPars.put("email", user.getEmail());
        mapPars.put("address", user.getEmail());
        mapPars.put("phoneNumber1", user.getEmail());
        mapPars.put("phoneNumber2", user.getEmail());
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

        mapPars.put("login", login);

        final String qryStr="DELETE FROM ml_users WHERE login=:login";

        namedParamTemplate.update(qryStr, mapPars);
    }

    @Override
    public void addRole(User user, Role role) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("INSERT INTO ml_user_role_ref ");
        qryStrBuilder.append("(user_id,role_id)");
        qryStrBuilder.append(" VALUES ");
        qryStrBuilder.append("(:user_id,:role_id) ");
        qryStrBuilder.append("ON DUPLICATE KEY UPDATE role_id=:role_id");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("user_id", user.getId());
        mapPars.put("role_id", role.getId());

        namedParamTemplate.update(qryStr,mapPars);

    }

    @Override
    public void removeRole(User user, Role role) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("user_id",user.getId());
        mapPars.put("role_id",role.getId());

        final String qryStr="DELETE FROM ml_user_role_ref WHERE user_id=:user_id AND role_id=:role_id";

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public List<Role> getUserRoles(User user) {
        final String qryStr="SELECT * FROM ml_users_roles WHERE user_id=:user_id";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("user_id",user.getId());

        return namedParamTemplate.query(qryStr,mapPars, roleRowMapper);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        User usr=findByLogin(login);

        return usr.getPassword().equals(password);
    }

}
