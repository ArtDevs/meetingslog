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

    public void createMessage(Message msg){}

    public void deleteMessage(Message msg){}

    public void editMessage(Message msg, String newMessageBody){}

    public List<Message> getMessagesByUser(User user){
        List<Message> list = new ArrayList<>();
        return list;
    }

    public List<Message> getMessagesByGroup(String groupId){
        List<Message> list = new ArrayList<>();
        return list;
    }

    public List<Message> getMessagesByUserAndGroup(User user, String groupId){
        List<Message> list = new ArrayList<>();
        return list;
    }

    public Message getMessagesById(int MessageId){
        return
    }

    public List<Message> getAllMessages(){
        List<Message> list = new ArrayList<>();
        return list;
    }
}
