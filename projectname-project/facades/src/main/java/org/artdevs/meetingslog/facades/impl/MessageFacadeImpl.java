package org.artdevs.meetingslog.facades.impl;

import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.facades.MessageFacade;
import org.artdevs.meetingslog.services.MessageService;
import org.artdevs.meetingslog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Яна on 30.01.15.
 */
@Component
public class MessageFacadeImpl implements MessageFacade {

    @Autowired
    MessageService messageService;

    @Autowired
    UserServices userServices;

    public void createMessage(Message msg) {
        messageService.createMessage(msg);
    }

    public void deleteMessage(Message msg) {
        if (msg != null) {
            messageService.removeMessage(msg);
        }
    }

    public void editMessage(Message msg, String newMessageBody) {
        Message msgFromDb = messageService.getMessageById(msg.getId());
        if(msgFromDb != null && messageService.isMessageEditable(msgFromDb)){
            messageService.editMessage(String.valueOf(msgFromDb.getId()), newMessageBody);
        }
    }

    public List<Message> getMessagesByUser(User user) {
        User userFromDb = userServices.findUserByName(user.getLogin());
        List <Message> list = new ArrayList<>();
        if(userFromDb != null){
            list = messageService.getMessagesByOwner(String.valueOf(userFromDb.getId()));
        }
        return list;
    }

    public List<Message> getMessagesByGroup(String groupId) {
        throw new UnsupportedOperationException("getMessagesByGroup Not yet implemented!!");
    }

    public List<Message> getMessagesByUserAndGroup(User user, String groupId) {
        throw new UnsupportedOperationException("getMessagesByUserAndGroup Not yet implemented!!");
    }

    public Message getMessageById(int MessageId) {
        Message msg;
        msg = messageService.getMessageById(MessageId);
        if(msg != null){
            return msg;
        }
        else return null;
    }

    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
