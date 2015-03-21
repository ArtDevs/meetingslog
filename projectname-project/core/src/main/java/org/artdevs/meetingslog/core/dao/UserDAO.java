package org.artdevs.meetingslog.core.dao;

import org.artdevs.meetingslog.core.model.Role;
import org.artdevs.meetingslog.core.model.UserModel;

import java.util.List;

/**
 * Created by Slava on 10.12.2014.
 */
public interface UserDAO {
    public List<UserModel> getAll();
    public List<UserModel> getByEmail(String email);

    public UserModel findById(int id);
    public UserModel findByLogin(String login);

    public void insert(UserModel userModel);

    public void updateById(UserModel userModel);
    public void updateByLogin(UserModel userModel);

    public void removeById(int id);
    public void removeByLogin(String login);

    public List<Role> getUserRoles(UserModel userModel);

    public void addRole(UserModel userModel, Role role);
    public void removeRole(UserModel userModel, Role role);

    public boolean checkPassword(String login, String password);

}
