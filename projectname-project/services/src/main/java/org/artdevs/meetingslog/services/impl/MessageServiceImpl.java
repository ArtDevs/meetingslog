package org.artdevs.meetingslog.services.impl;

import org.artdevs.meetingslog.core.dao.MessageDAO;
import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.UserModel;
import org.artdevs.meetingslog.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Яна on 24.01.15.
 */
@Component
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageDAO messageDAO;

	@Autowired
	UserDAO userDAO;

	public void createMessage(Message msg) {

		messageDAO.insert(msg);
	}

	public void removeMessage(Message msg) {
		messageDAO.removeById(msg.getId());
	}

	public void removeMessage(String messageId) {
		if (messageDAO.findById(Integer.valueOf(messageId)) != null)
			messageDAO.removeById(Integer.valueOf(messageId));
	}

	public void removeMessages(String owner) {
		if (userDAO.findByLogin(owner) != null)
			messageDAO.removeByUser(userDAO.findByLogin(owner));
	}

	public void removeMessages(UserModel owner) {
		if (userDAO.getAll().contains(owner))
			messageDAO.removeByUser(owner);
	}

	public void editMessage(Message msg) {
		if (msg.isReadonly()) {
			System.out.println("Can't edit a message!");
		}
		messageDAO.updateById(msg);
	}

	public void editMessage(String messageId, String newMessageBody) {
		if (messageDAO.findById(Integer.valueOf(messageId)) != null && !(messageDAO.findById(Integer.valueOf(messageId)).isReadonly())) {
			Message msg = messageDAO.findById(Integer.valueOf(messageId));
			msg.setMsg_text(newMessageBody);
		} else {
			System.out.println("Can't edit message");
		}

	}

	public List<Message> getAllMessages() {
		return messageDAO.getAll();
	}

	public List<Message> getMessagesByOwner(String ownerId) {
		List<Message> msgList = new ArrayList<Message>();
		if (userDAO.findById(Integer.valueOf(ownerId)) != null) {
			msgList = messageDAO.getByUser(userDAO.findById(Integer.valueOf(ownerId)));
			return msgList;
		} else {
			return msgList = null;
		}
	}

	public List<Message> getMessagesByGroup(String groupId) {
		throw new UnsupportedOperationException("getMessagesByGroup Not yet implemented!!");
//        List<Message> msgList;
//        if (userDAO.findById(Integer.valueOf(groupId)) != null) {
//            msgList = messageDAO.getByUser(userDAO.findById(Integer.valueOf(groupId)));        //we don't have method getByGroup
//            return msgList;                                                                     //so I make this byUser for now
//        } else {
//            return msgList = null;
//        }
	}

	public boolean isMessageEditable(String msgId) {
		if (messageDAO.findById(Integer.valueOf(msgId)) != null && !(messageDAO.findById(Integer.valueOf(msgId)).isReadonly())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isMessageEditable(Message msg) {
		if (msg.isReadonly()) {
			return false;
		} else
			return true;
	}
}
