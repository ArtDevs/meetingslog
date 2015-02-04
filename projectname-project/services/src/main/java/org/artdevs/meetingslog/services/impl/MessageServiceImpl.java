package org.artdevs.meetingslog.services.impl;

import org.artdevs.meetingslog.core.dao.MessageDAO;
import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
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
        User user = userDAO.findByLogin(owner);
        if (user != null)
            messageDAO.removeByUser(user);
    }

    public void removeMessages(User owner) {
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
        Message msg = messageDAO.findById(Integer.valueOf(messageId));
        if (msg != null && !(msg.isReadonly())) {
            msg.setMsg_text(newMessageBody);
        } else {
            System.out.println("Can't edit message");
        }

    }

    public List<Message> getAllMessages() {
        return messageDAO.getAll();
    }


    public Message getMessageById(int MessageId) {
        Message msg = messageDAO.findById(MessageId);
        if(msg != null){
            return msg;
        }
        else return null;
    }

    public List<Message> getMessagesByOwner(String ownerId) {
        List<Message> msgList;
        User user = userDAO.findById(Integer.valueOf(ownerId));
        if (user != null) {
            msgList = messageDAO.getByUser(user);
            return msgList;
        } else {
            return Collections.emptyList();
        }
    }

    public List<Message> getMessagesByGroup(String groupId) {
        throw new UnsupportedOperationException("getMessagesByGroup Not yet implemented!!");
    }

    public boolean isMessageEditable(String msgId) {
        Message msg = messageDAO.findById(Integer.valueOf(msgId));
        if (msg != null && !(msg.isReadonly())) {
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
