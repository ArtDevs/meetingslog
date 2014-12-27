package org.artdevs.meetingslog.facades.impl;

import org.artdevs.meetingslog.core.dao.RoleDAO;
import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.Role;
import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.facades.MapFormData;
import org.artdevs.meetingslog.facades.facademodel.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by Slava on 25.12.2014.
 */

@Component
public class MapUser implements MapFormData<UserFacade,User> {

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public User mapToModel(UserFacade data) throws RuntimeException {

        User res=new User();
        Role r=new Role();

        try {
            res.setLogin(data.getLogin());
            res.setRole(roleDAO.findByName("guest"));
            res.setPassword(data.getPassword());
            res.setFirstName(data.getFirstName());
            res.setSecondName(data.getSecondName());
            res.setEmail(data.getEmail());
            res.setComment(data.getComment());
        }catch (RuntimeException e){
            return null;
        }

        return res;
    }

    @Override
    public boolean verify(UserFacade data) {

        return userDAO.findByLogin(data.getLogin())==null;

    }
}
