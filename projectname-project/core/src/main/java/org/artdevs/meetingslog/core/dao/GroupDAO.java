package org.artdevs.meetingslog.core.dao;

import org.artdevs.meetingslog.core.model.Group;
import org.artdevs.meetingslog.core.model.User;

import java.util.List;

/**
 * Created by Slava on 24.01.2015.
 */
public interface GroupDAO {
    public List<Group> getAll();
    public List<Group> getByOwner(User user);
    public Group findById(int id);
    public void insert(Group group);
    public void updateById(Group group);
    public void removeById(int id);
    public void removeByOwner(User user);
    public Group createGroup(String name, List<User> userList);

    public void changeOwner(Group group, User newOwner);
    public void addUser(Group group, User user);
    public void removeUser(Group group, User user);
    public List<Group> getByUser(User user);
    public List<User> getUsers(Group group);
}
