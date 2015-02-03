package org.artdevs.meetingslog.facades.tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;
import org.artdevs.meetingslog.facades.MessageFacade;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Яна on 31.01.15.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
                "classpath*:/META-INF/custom-config/*-custom-spring.xml",
                "classpath*:/META-INF/config/meetingslog-*-config.xml"
        }
)
public class MessageFacadeTest {
    @Autowired
    private MessageFacade messageFacade;

    private Message msgNotEditable;
    private Message msgEditable;
    private User user;
    private List<Message> beforeList = new ArrayList<>();
    private List<Message> afterList = new ArrayList<>();
    private List<Message> groupMsgList = new ArrayList<>();
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
        messageFacade.createMessage(msgEditable);
        messageFacade.createMessage(msgNotEditable);

    }

    @Test
    public void testProcessMessage() {
        assertNotNull("Your messageFacade is NULL", messageFacade);
        Message msg = new Message(-3,false, "Some title", "Some text");
        //Creating messages
        beforeList = messageFacade.getMessagesByUser(user);
        messageFacade.createMessage(msg);
        afterList = messageFacade.getMessagesByUser(user);
        TestCase.assertEquals("Create message failed", afterList.size() - beforeList.size(), 1);
        //Deleting messages
        beforeList = messageFacade.getMessagesByUser(user);
        messageFacade.deleteMessage(msg);
        afterList = messageFacade.getMessagesByUser(user);
        TestCase.assertEquals("Delete message failed", beforeList.size() - afterList.size(), 1);
    }

   /* @Test
    public void testDeleteMessage() {
        beforeList = messageFacade.getAllMessages();
        messageFacade.deleteMessage(msgEditable);
        afterList = messageFacade.getAllMessages();
        TestCase.assertEquals(beforeList.size() - afterList.size() , 1);

    }*/


    @Test
    public void testEditMessage() {
        userMsgList = messageFacade.getMessagesByUser(user); //list of our messages from DB

        assertNotNull("This user don't have any massages",userMsgList);


        Message newEditableMessage = messageFacade.getMessagesById(-1);    //work with our messages from BD
        Message newNotEditableMessage = messageFacade.getMessagesById(-2);

        assertTrue("Test on editable of your message is failed", newEditableMessage.isReadonly());
        assertFalse("Test on not editable of your message is failed", newNotEditableMessage.isReadonly());

        String oldEditableTextMsg = newEditableMessage.getMsg_text();
        messageFacade.editMessage(newEditableMessage, "New Text");
        String newEditableTextMsg = newEditableMessage.getMsg_text();

        assertFalse("Editing message is failed", oldEditableTextMsg.equals(newEditableTextMsg));

        /*String oldNotEditableTextMsg = newNotEditableMessage.getMsg_text();*/
        exception.expect(Exception.class);
        messageFacade.editMessage(newNotEditableMessage, "New Text");

        /*String newNotEditableTextMsg = newNotEditableMessage.getMsg_text();*/




        /*Message someMsg = messageFacade.getAllMessages().get(0); //check with DB work
        if (someMsg.isReadonly()) {
            String oldMsg = someMsg.getMsg_text();
            someMsg.setMsg_text("New text");
            assertTrue("Editing message from DB is failed", oldMsg.equals(someMsg.getMsg_text()));
        } else {
            String oldMsg = someMsg.getMsg_text();
            someMsg.setMsg_text("New text");
            assertFalse("Check on not editing message from DB is failed", oldMsg.equals(someMsg.getMsg_text()));
        }
        */
    }

    @Test
    public void testGetMessagesByUser() {
        assertNotNull("Getting messages from DB by user is failed", messageFacade.getMessagesByUser(user));

    }

    @Ignore
    @Test
    public void testGetMessagesByGroup() {
        assertNotNull("Getting messages from DB by group is failed", messageFacade.getMessagesByGroup(""));
    }

    @Ignore
    @Test
    public void testGetMessagesByUserAndGroup() {
        groupMsgList = messageFacade.getMessagesByGroup("");
        userMsgList = messageFacade.getMessagesByUser(user);
        assertNotNull("There is no messages in group", groupMsgList);
        assertNotNull("There is no messages of user", userMsgList);
        assertTrue("Getting user messages from group failed", groupMsgList.containsAll(userMsgList));
    }

    @Test
    public void testGetAllMessages() {
        assertNotNull("There is no messages at all", messageFacade.getAllMessages());
    }

    @After
    public void cleanDB(){
        messageFacade.deleteMessage(msgEditable);
        messageFacade.deleteMessage(msgNotEditable);
    }
}
