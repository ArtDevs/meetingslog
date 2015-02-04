package org.artdevs.meetingslog.facades;

import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;

import java.util.List;

/**
 * Created by Яна on 30.01.15.
 */

public interface MessageFacade {

    public void createMessage(Message msg);

    public void deleteMessage(Message msg);

    public void editMessage(Message msg, String newMessageBody);

    public List<Message> getMessagesByUser(User user);

    public Message getMessageById(int MessageId);

    public List<Message> getMessagesByGroup(String groupId);

    public List<Message> getMessagesByUserAndGroup(User user, String groupId);

    public List<Message> getAllMessages();

}
