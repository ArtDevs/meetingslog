package org.artdevs.meetingslog.services.impl;

import org.artdevs.meetingslog.core.dao.RoleDAO;
import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Slava on 26.12.2014.
 */
@Component
public class UserServicesImpl implements UserServices {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Override
    public boolean insertUser(User user) throws RuntimeException {
        if(userDAO.findByLogin(user.getLogin())!=null) {
            return false;
        }
        else{
            userDAO.insert(user);
            userDAO.addRole(user,roleDAO.findByName("guest"));
            return true;
        }
    }

    @Override
    public boolean changeUserInfo(User user) throws RuntimeException {
        if(userDAO.findByLogin(user.getLogin())==null) {
            return false;
        }
        else{
            userDAO.updateByLogin(user);
            return true;
        }
    }

    @Override
    public boolean deleteUser(User user) throws RuntimeException {
        userDAO.removeById(user.getId());
        return true;
    }
}
