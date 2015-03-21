package org.artdevs.meetingslog.core.dao;

import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.UserModel;

import java.util.List;

/**
 * Created by Slava on 13.01.2015.
 */
public interface MessageDAO {
    public List<Message> getAll();
    public List<Message> getByUser(UserModel userModel);
    public Message findById(int id);
    public void insert(Message message);
    public void updateById(Message message);
    public void removeById(int id);
    public void removeByUser(UserModel userModel);
    public void toggleReadonly(Message message, boolean status);
}
