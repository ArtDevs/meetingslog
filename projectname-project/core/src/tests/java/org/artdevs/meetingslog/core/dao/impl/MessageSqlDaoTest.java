package org.artdevs.meetingslog.core.dao.impl;

import junit.framework.TestCase;
import org.artdevs.meetingslog.core.model.Message;
import org.artdevs.meetingslog.core.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
                "classpath*:/META-INF/custom-config/*-custom-spring.xml",
                "classpath*:/META-INF/config/meetingslog-*-config.xml"
        }
)

public class MessageSqlDaoTest extends TestCase {

    @Autowired
    MessageSqlDao messageSqlDao;

    @Autowired
    UserSqlDao userSqlDao;

    int numInitMsg;//messageSqlDao.getAll()==null?0:messageSqlDao.getAll().size();
    int numTestMsg;
    User user;
    List<Integer> insertedIDs=new ArrayList<>(numTestMsg);

    @Before
    public void setUp() throws Exception {

        //super.setUp();

        numInitMsg=messageSqlDao.getAll()==null?0:messageSqlDao.getAll().size();
        numTestMsg=10;
        user=userSqlDao.findByLogin("admin");

        if(user==null) throw (new Exception("Failed to get default user (admin)"));

        Message msg=new Message(user.getId());
        for(int i=0; i<numTestMsg; i++){
            msg.setMsg_title("Test message "+i);
            msg.setMsg_text("Message, created to test DAO.");
            msg.setReadonly(false);

            messageSqlDao.insert(msg);
            insertedIDs.add(msg.getId());
        }

    }

    @After
    public void tearDown() throws Exception {

        for (Integer i:insertedIDs){
            messageSqlDao.removeById(i);
        }
    }


    @Test
    public void testGetAll() throws Exception {

        assertEquals(numTestMsg+numInitMsg,messageSqlDao.getAll().size());
    }

    @Test
    public void testGetByUser() throws Exception {
        assertTrue(messageSqlDao.getByUser(user).size()>=numTestMsg &&
                messageSqlDao.getByUser(user).size()<=messageSqlDao.getAll().size());
    }

    @Test
    public void testFindById() throws Exception {
        Random random=new Random(numTestMsg);
        Message msg=messageSqlDao.findById(insertedIDs.get(random.nextInt()%numTestMsg));
        assertTrue(msg.getMsg_title().contains("Test message"));
    }

    @Test
    public void testInsert() throws Exception {
        Message msg=new Message(user.getId());
        msg.setMsg_title("Test message "+(numTestMsg));
        msg.setMsg_text("Message, created to test DAO.");
        msg.setReadonly(false);

        messageSqlDao.insert(msg);
        insertedIDs.add(msg.getId());

        assertEquals(messageSqlDao.findById(insertedIDs.get(numTestMsg)).getMsg_title(),
                ("Test message "+numTestMsg));

    }

    @Test
    public void testUpdateById() throws Exception {
        final String newMsgText="Corrected message text.";

        Message msg=messageSqlDao.findById(insertedIDs.get(0));
        msg.setMsg_text(newMsgText);
        messageSqlDao.updateById(msg);

        assertEquals(newMsgText,messageSqlDao.findById(insertedIDs.get(0)).getMsg_text());
    }

    @Test
    public void testRemoveById() throws Exception {
        messageSqlDao.removeById(insertedIDs.get(numTestMsg-1));

        assertNull(messageSqlDao.findById(insertedIDs.get(numTestMsg - 1)));

        insertedIDs.remove(numTestMsg-1);
    }

    @Ignore
    @Test
    public void testRemoveByUser() throws Exception {

    }

    @Ignore
    @Test
    public void testToggleReadonly() throws Exception {

    }
}