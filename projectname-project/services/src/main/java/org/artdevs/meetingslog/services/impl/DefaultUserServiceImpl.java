package org.artdevs.meetingslog.services.impl;

import org.artdevs.meetingslog.core.dao.RoleDAO;
import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.UserModel;
import org.artdevs.meetingslog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Slava on 26.12.2014.
 */
@Component
public class DefaultUserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public boolean insertUser(UserModel userModel) throws RuntimeException {
        if(userDAO.findByLogin(userModel.getLogin())!=null) {
            return false;
        }
        else{
            userDAO.insert(userModel);
            userDAO.addRole(userModel,roleDAO.findByName("guest"));
            return true;
        }
    }

    @Override
    public boolean changeUserInfo(UserModel userModel) throws RuntimeException {
        if(userDAO.findByLogin(userModel.getLogin())==null) {
            return false;
        }
        else{
            userDAO.updateByLogin(userModel);
            return true;
        }
    }

    @Override
    public boolean deleteUser(UserModel userModel) throws RuntimeException {
        userDAO.removeById(userModel.getId());
        return true;
    }

    @Override
    public boolean checkPassword(String login, String password) {
        return false;
    }

    @Override
    public UserModel findUserByLogin(String login) {
        return userDAO.findByLogin(login);
    }
}
