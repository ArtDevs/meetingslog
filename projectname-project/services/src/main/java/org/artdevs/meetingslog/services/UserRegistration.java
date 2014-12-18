package org.artdevs.meetingslog.services;

import org.artdevs.meetingslog.model.User;

import java.util.List;

/**
 * Created by Slava on 16.12.2014.
 */
public interface UserRegistration {
    public String createUser(User user);
    public String updateUser(User user);
    public boolean removeUser(User user);
    public List<User> getAllUsers();
    public User findUserByName(String name);
    public List<User> findUserByEmail(String email);
    public boolean checkPassword(String login, String password);
}
