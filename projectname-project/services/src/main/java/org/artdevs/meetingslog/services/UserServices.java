package org.artdevs.meetingslog.services;

import org.artdevs.meetingslog.core.model.User;

/**
 * Created by Slava on 26.12.2014.
 */
public interface UserServices {
    public boolean insertUser(User user) throws RuntimeException;
    public boolean changeUserInfo(User user) throws RuntimeException;
    public boolean deleteUser(User user) throws RuntimeException;
    public boolean checkPassword(String login, String password);
    public User findUserByName(String login);
}
