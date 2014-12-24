package org.artdevs.meetingslog.core.dao;

import org.artdevs.meetingslog.core.model.Role;

import java.util.List;

/**
 * Created by Slava on 22.12.2014.
 */
public interface RoleDAO {
    public List<Role> getAll();

    public Role findById(int id);
    public Role findByName(String name);

    public void insert(Role user);

    public void updateById(Role user);
    public void updateByName(Role user);

    public void removeById(int id);
    public void removeByName(String name);
}
