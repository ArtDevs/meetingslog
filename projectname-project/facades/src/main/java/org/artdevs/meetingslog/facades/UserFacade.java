package org.artdevs.meetingslog.facades;

import org.artdevs.meetingslog.core.model.UserModel;
import org.artdevs.meetingslog.facades.data.UserData;

/**
 * Created by Slava on 26.12.2014.
 */
public interface UserFacade {
    public boolean insertUser(UserData user) throws RuntimeException;
    public boolean changeUserInfo(UserData user) throws RuntimeException;
    public boolean deleteUser(UserData user) throws RuntimeException;
    public boolean checkPassword(String login, String password);
    public UserData findUserByLogin(String login);
    public boolean verify(UserData data);
}
