package org.artdevs.meetingslog.services.impl;

import org.artdevs.meetingslog.dao.UserDAO;
import org.artdevs.meetingslog.model.User;
import org.artdevs.meetingslog.services.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Slava on 16.12.2014.
 */
@Component("sqlRegistrationComponent")
public class UserSqlRegistration implements UserRegistration {

    @Autowired
    UserDAO userSqlDao;

    @Override
    public String createUser(User user) {
        User tmp=userSqlDao.findByLogin(user.getLogin());
        if(tmp!=null){
            return "Already exists.";
        }
        else{
            try{
                userSqlDao.insert(user);
            }catch (SQLException e){
                return "Database error: "+e.getMessage();
            }
            return "Success.";
        }
    }

    @Override
    public String updateUser(User user) {
        User tmp=userSqlDao.findByLogin(user.getLogin());
        if(tmp==null){
            return "No such user.";
        }
        else{
            try{
                userSqlDao.updateByLogin(user);
            }catch (SQLException e){
                return "Database error: "+e.getMessage();
            }
            return "Success.";
        }
    }

    @Override
    public boolean removeUser(User user) {
        try {
            userSqlDao.removeByLogin(user.getLogin());
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userSqlDao.getAll();
    }

    @Override
    public User findUserByName(String name) {
        return userSqlDao.findByLogin(name);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userSqlDao.getByEmail(email);
    }

    @Override
    public boolean checkPassword(String login, String password) {
        return userSqlDao.checkPassword(login,password);
    }
}
