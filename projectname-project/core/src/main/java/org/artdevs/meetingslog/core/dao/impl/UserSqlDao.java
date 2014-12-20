package org.artdevs.meetingslog.core.dao.impl;

import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Slava on 11.12.2014.
 */

//@Component
public class UserSqlDao implements UserDAO{

    @Autowired
    private DriverManagerDataSource dataSrc;

    private Resource rec;

    @Autowired
    NamedParameterJdbcTemplate namedParamTemplate;

//    @Value("${jdbc.schema}")
    public void setRec(Resource rec) {
        this.rec = rec;
    }

    private RowMapper<User> userRowMapper=new RowMapper<User>(){
        public User mapRow (ResultSet res,int rowNum)throws SQLException {
            return new User(
                    res.getInt("id"),
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

    @PostConstruct
    public void initialize(){
        System.err.println("==========================" + rec);
        try{

            InputStream iStream=rec.getInputStream();
            Scanner scan=new Scanner(iStream);
            StringBuilder sqlStr=new StringBuilder();

            while(scan.hasNext()){
                sqlStr.append(scan.nextLine()).append("\n");
            }
            scan.close();
            iStream.close();

            Connection conn=null;
            Statement query=null;

            try{
                conn=dataSrc.getConnection();
                query=conn.createStatement();
                query.execute(sqlStr.toString());

            }catch(SQLException exept){
                throw new RuntimeException("SQL error creating tables",exept);
            }finally {
                try {
                    if (conn != null)
                        conn.close();
                }catch(SQLException exept){}
            }

        }catch(IOException exept){
            throw new RuntimeException("Bad resource",exept);
        }
    }

    @Override
    public List<User> getAll(){
        final String qryStr="SELECT * FROM users";

        return namedParamTemplate.query(qryStr, userRowMapper);
    }

    @Override
    public List<User> getByEmail(String email) {
        final String qryStr="SELECT * FROM users WHERE email=:email";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("email",email);

        return namedParamTemplate.query(qryStr,mapPars, userRowMapper);
    }

    @Override
    public User findById(int id) {
        final String qryStr="SELECT * FROM users WHERE id=:id";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("id",id);

        try {
            return namedParamTemplate.queryForObject(qryStr, mapPars, userRowMapper);
        }catch (Exception e){return null;}
    }

    @Override
    public User findByLogin(String login) {
        final String qryStr="SELECT * FROM users WHERE login=:login";

        Map<String,Object> mapPars=new HashMap<String,Object>();
        mapPars.put("login",login);

        try {
            return namedParamTemplate.queryForObject(qryStr, mapPars, userRowMapper);
        }catch (Exception e){return null;}
    }

    @Override
    public void insert(User user) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("INSERT INTO users ");
        qryStrBuilder.append("(login,password,firstName,secondName,email,comment,tmLastLogin,tmRegistered)");
        qryStrBuilder.append(" VALUES ");
        qryStrBuilder.append("(:login,:password,:firstName,:secondName,:email,:comment,:tmLastLogin,:tmRegistered)");

        final String qryStr=qryStrBuilder.toString();

        Map<String,Object> mapPars=new HashMap<String,Object>();

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
    public void updateById(User user) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE users SET ");
        qryStrBuilder.append("login=:login,password=:password,firstName=:firstName,secondName=:secondName,email=:email,");
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
        mapPars.put("comment", user.getComment());
        mapPars.put("tmLastLogin", user.getTmLastLogin());
        mapPars.put("tmRegistered", user.getTmRegistered());

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void updateByLogin(User user) {
        StringBuilder qryStrBuilder=new StringBuilder();
        qryStrBuilder.append("UPDATE users SET ");
        qryStrBuilder.append("id=:id,password=:password,firstName=:firstName,secondName=:secondName,email=:email,");
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
        mapPars.put("comment", user.getComment());
        mapPars.put("tmLastLogin", user.getTmLastLogin());
        mapPars.put("tmRegistered", user.getTmRegistered());

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void removeById(int id) {

        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("id",id);

        final String qryStr="DELETE FROM users WHERE id=:id";

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public void removeByLogin(String login) {
        Map<String,Object> mapPars=new HashMap<String,Object>();

        mapPars.put("login",login);

        final String qryStr="DELETE FROM users WHERE login=:login";

        namedParamTemplate.update(qryStr,mapPars);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        User usr=findByLogin(login);

        return usr.getPassword().equals(password);
    }
}
