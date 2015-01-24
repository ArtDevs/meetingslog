package org.artdevs.meetingslog.facades.impl;

import org.artdevs.meetingslog.core.dao.RoleDAO;
import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.Role;
import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.facades.MapFormData;
import org.artdevs.meetingslog.facades.data.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Slava on 25.12.2014.
 */

@Component
public class MapUser implements MapFormData<UserData,User> {

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public User mapToModel(UserData data) throws RuntimeException {

        User res=new User();
        Role r=new Role();

        try {
            res.setLogin(data.getLogin());
            res.setPassword(data.getPassword());
            res.setFirstName(data.getFirstName());
            res.setSecondName(data.getSecondName());
            res.setEmail(data.getEmail());
            res.setAddress(data.getAddress());
            res.setPhoneNumber1(data.getPhoneNumber1());
            res.setPhoneNumber2(data.getPhoneNumber2());
            res.setComment(data.getComment());
        }catch (RuntimeException e){
            return null;
        }

        return res;
    }

    @Override
    public boolean verify(UserData data) {

        return userDAO.findByLogin(data.getLogin())==null;

    }
}
