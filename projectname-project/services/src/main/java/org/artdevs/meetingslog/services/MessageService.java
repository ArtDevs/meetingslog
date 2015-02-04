package org.artdevs.meetingslog.services;

import java.util.List;
import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;

/**
 * Created by Artem L.V. on 1/26/15.
 */
public interface MessageService {

	public void createMessage(Message msg);

	public void removeMessage(Message msg);

	public void removeMessage(String messageId);

	public void removeMessages(String owner);

	public void removeMessages(User owner);

	public void editMessage(Message msg);

	public void editMessage(String messageId, String newMessageBody);

	public List<Message> getAllMessages();

    public Message getMessageById(int MessageId);

	public List<Message> getMessagesByOwner(String ownerId);

	public List<Message> getMessagesByGroup(String groupId);

	public boolean isMessageEditable(String msgId);

	public boolean isMessageEditable(Message msg);
}
