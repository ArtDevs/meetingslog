package org.artdevs.meetingslog.core.dao;

import org.artdevs.meetingslog.core.model.Group;
import org.artdevs.meetingslog.core.model.User;

import java.util.List;

/**
 * Created by Slava on 24.01.2015.
 */
public interface GroupDAO {
    public List<Group> getAll();
    public List<Group> getByUser(User user);
    public Group findById(int id);
    public void insert(Group group);
    public void updateById(Group group);
    public void removeById(int id);
    public void removeByUser(User user);
}
