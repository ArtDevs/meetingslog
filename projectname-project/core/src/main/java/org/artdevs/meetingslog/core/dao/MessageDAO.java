package org.artdevs.meetingslog.core.dao;

import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;

import java.util.List;

/**
 * Created by Slava on 13.01.2015.
 */
public interface MessageDAO {
    public List<Message> getAll();
    public List<Message> getByUser(User user);
    public Message findById(int id);
    public void insert(Message message);
    public void updateById(Message message);
    public void removeById(int id);
    public void removeByUser(User user);
    public void toggleReadonly(Message message, boolean status);
}
