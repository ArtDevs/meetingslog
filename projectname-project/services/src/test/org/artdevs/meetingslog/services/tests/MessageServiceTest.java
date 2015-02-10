package org.artdevs.meetingslog.services.tests;

import junit.framework.TestCase;
import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.services.MessageService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Яна on 10.02.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
                "classpath*:/META-INF/custom-config/*-custom-spring.xml",
                "classpath*:/META-INF/config/meetingslog-*-config.xml"
        }
)

public class MessageServiceTest {
    @Autowired
    MessageService messageService;

    private Message msgNotEditable;
    private Message msgEditable;
    private User user;
    private List<Message> beforeList = new ArrayList<>();
    private List<Message> afterList = new ArrayList<>();
    private List<Message> userMsgList = new ArrayList<>();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void SetUpData() {
        user = new User(-1, "login", "Pass", "First Name", "Second Name", "some email", "Some adress",
                "Some number", "another number", "Some comment", new Date(), new Date());
        msgEditable = new Message(-1, false, "Some title", "Some text");
        msgNotEditable = new Message(-2, true, "Some title", "Some text");
        msgEditable.setOwnerId(-1);
        msgNotEditable.setOwnerId(-1);
        messageService.createMessage(msgEditable);
        messageService.createMessage(msgNotEditable);

    }

    @Test
    public void testCreateMessage(){
        assertNotNull("Your message is NULL", msgEditable);
        beforeList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        messageService.createMessage(msgEditable);
        afterList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        TestCase.assertEquals("Create message failed", afterList.size() - beforeList.size(), 1);
        messageService.removeMessage(msgEditable);
    }

    @Test
    public void testRemoveMessage(){
        //remove by message
        assertNotNull("Your message is NULL", msgEditable);
        messageService.createMessage(msgEditable);
        beforeList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        messageService.removeMessage(msgEditable);
        afterList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        TestCase.assertEquals("Remove message by message failed", beforeList.size() - afterList.size(), 1);

        //remove by message ID
        assertNotNull("Your message is NULL", msgEditable);
        messageService.createMessage(msgEditable);
        beforeList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        messageService.removeMessage(String.valueOf(msgEditable.getId()));
        afterList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        TestCase.assertEquals("Remove message by message ID failed", beforeList.size() - afterList.size(), 1);

        //remove by owner
        assertNotNull("Your message is NULL", msgEditable);
        assertNotNull("Your owner is NULL", user);
        messageService.createMessage(msgEditable);
        beforeList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        messageService.removeMessage(user.getLogin());
        afterList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        TestCase.assertEquals("Remove message by message owner failed", beforeList.size() - afterList.size(), 1);

        //remove by user
        assertNotNull("Your message is NULL", msgEditable);
        assertNotNull("Your user is NULL", user);
        messageService.createMessage(msgEditable);
        beforeList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        messageService.removeMessages(user);
        afterList = messageService.getMessagesByOwner(String.valueOf(user.getId()));
        TestCase.assertEquals("Remove message by message owner failed", beforeList.size() - afterList.size(), 1);

    }

    @Test
    public void testEditMessage() {
        userMsgList = messageService.getMessagesByOwner(user.getLogin()); //list of our messages from DB

        assertNotNull("This user don't have any massages",userMsgList);


        Message newEditableMessage = messageService.getMessageById(-1);    //work with our messages from BD
        Message newNotEditableMessage = messageService.getMessageById(-2);

        assertFalse("Test on editable of your message is failed", newEditableMessage.isReadonly());
        assertTrue("Test on not editable of your message is failed", newNotEditableMessage.isReadonly());

        String oldEditableTextMsg = newEditableMessage.getMsg_text();
        messageService.editMessage(String.valueOf(newEditableMessage.getId()), "New Text");
        String newEditableTextMsg = newEditableMessage.getMsg_text();

        assertFalse("Editing message is failed", oldEditableTextMsg.equals(newEditableTextMsg));

        /*String oldNotEditableTextMsg = newNotEditableMessage.getMsg_text();*/
        exception.expect(Exception.class);
        messageService.editMessage(String.valueOf(newNotEditableMessage.getId()), "New Text");

    }

    @Test
    public void testGetMessageById(){
        assertNotNull("Getting messages from DB by message ID is failed", messageService.getMessageById(-1));
    }

    @Test
    public void testGetMessagesByOwnerId() {
        assertNotNull("Getting messages from DB by owner ID is failed", messageService.getMessagesByOwner(String.valueOf(user.getId())));
    }

    @Ignore
    @Test
    public void testGetMessagesByGroup() {
        assertNotNull("Getting messages from DB by group is failed", messageService.getMessagesByGroup(""));
    }

    @Test
    public void testGetAllMessages() {
        assertNotNull("There is no messages at all", messageService.getAllMessages());
    }

    @Test
    public void testisMessageEditable(){
        //by massage
        assertTrue("Editable message is not editable", messageService.isMessageEditable(msgEditable));
        assertFalse("Not editable message is editable", messageService.isMessageEditable(msgNotEditable));
        //by massage ID
        assertTrue("Editable message is not editable", messageService.isMessageEditable(String.valueOf(msgEditable.getId())));
        assertFalse("Not editable message is editable", messageService.isMessageEditable(String.valueOf(msgNotEditable.getId())));
    }

    @After
    public void cleanDB(){
        messageService.removeMessage(msgEditable);
        messageService.removeMessage(msgNotEditable);
    }
}
