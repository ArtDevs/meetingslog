package org.artdevs.meetingslog.services;

import org.artdevs.meetingslog.core.model.UserModel;

/**
 * Created by Slava on 26.12.2014.
 */
public interface UserService {
    public boolean insertUser(UserModel userModel) throws RuntimeException;
    public boolean changeUserInfo(UserModel userModel) throws RuntimeException;
    public boolean deleteUser(UserModel userModel) throws RuntimeException;
    public boolean checkPassword(String login, String password);
    public UserModel findUserByLogin(String login);
}
