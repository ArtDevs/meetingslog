package org.artdevs.meetingslog.core.dao.impl;

import junit.framework.TestCase;
import org.artdevs.meetingslog.core.dao.GroupDAO;
import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.Group;
import org.artdevs.meetingslog.core.model.User;
import org.junit.After;
import org.junit.Before;
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

public class GroupSqlDAOTest extends TestCase {

    @Autowired
    GroupDAO groupDao;

    @Autowired
    UserDAO userDao;

    int   numInitGrp;//messageSqlDao.getAll()==null?0:messageSqlDao.getAll().size();
    int   numTestGrp;
    User user;
    List<Integer> insertedIDs=new ArrayList<>(numTestGrp);

    @Before
    public void setUp() throws Exception {
        //super.setUp();
        numInitGrp= groupDao.getAll()==null?0: groupDao.getAll().size();
        numTestGrp=10;
        user= userDao.findByLogin("admin");

        if(user==null) throw (new Exception("Failed to get default user (admin)"));

        Group grp=new Group();
        for(int i=0; i<numTestGrp; i++){
            grp.setName("Test group " + i);
            grp.setOwner_id(user.getId());

            groupDao.insert(grp);
            insertedIDs.add(grp.getId());
        }
    }

    @After
    public void tearDown() throws Exception {
        for (Integer i:insertedIDs){
            groupDao.removeById(i);
        }
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(numTestGrp + numInitGrp, groupDao.getAll().size());
    }

    @Test
    public void testGetByUser() throws Exception {
        assertTrue(groupDao.getByOwner(user).size() >= numTestGrp &&
                groupDao.getByOwner(user).size() <= groupDao.getAll().size());
    }

    @Test
    public void testFindById() throws Exception {
        Random random=new Random(numTestGrp);
        Group grp= groupDao.findById(insertedIDs.get(random.nextInt()%numTestGrp));
        assertTrue(grp.getName().contains("Test group"));
    }

    @Test
    public void testInsert() throws Exception {
        Group grp=new Group();
        grp.setName("Test group " + (numTestGrp));
        grp.setOwner_id(user.getId());

        groupDao.insert(grp);
        insertedIDs.add(grp.getId());

        assertEquals(groupDao.findById(insertedIDs.get(numTestGrp)).getName(),
                ("Test group " + numTestGrp));

    }

    @Test
    public void testUpdateById() throws Exception {
        final String newGrpText="Corrected group name.";

        Group grp= groupDao.findById(insertedIDs.get(0));
        grp.setName(newGrpText);
        groupDao.updateById(grp);

        assertEquals(newGrpText, groupDao.findById(insertedIDs.get(0)).getName());

    }

    @Test
    public void testRemoveById() throws Exception {
        groupDao.removeById(insertedIDs.get(numTestGrp-1));

        assertNull(groupDao.findById(insertedIDs.get(numTestGrp - 1)));

        insertedIDs.remove(numTestGrp-1);

    }

    @Test
    public void testRemoveByUser() throws Exception {
        User user1=new User();
        user1.setLogin("testUserDelme");
        user1.setEmail("test@delme.usr");
        user1.setAddress("unknown address");
        user1.setPhoneNumber1("11111111111");
        user1.setPhoneNumber2("22222222222");
        user1.setComment("User for testing message DAO");
        user1.setSecondName("Userman");
        user1.setFirstName("Delme");
        user1.setPassword("123qwe");

        try {
            userDao.insert(user1);
        }catch (RuntimeException e){
            user1.setLogin("testUserDelme1");
            userDao.insert(user1);
        }

        for (Integer i:insertedIDs){
            Group grp= groupDao.findById(i);
            grp.setOwner_id(user1.getId());
            groupDao.updateById(grp);
        }

        groupDao.removeByOwner(user1);
        userDao.removeById(user1.getId());

        assertEquals(numInitGrp, groupDao.getAll().size());

    }

    @Test
    public void testGetByOwner() throws Exception {
        assertTrue(groupDao.getByOwner(user).size()>=numTestGrp);
    }

    @Test
    public void testCreateGroup() throws Exception {
        User user1=new User();
        user1.setLogin("testUserDelme");
        user1.setEmail("test@delme.usr");
        user1.setAddress("unknown address");
        user1.setPhoneNumber1("11111111111");
        user1.setPhoneNumber2("22222222222");
        user1.setComment("User for testing message DAO");
        user1.setSecondName("Userman");
        user1.setFirstName("Delme");
        user1.setPassword("123qwe");

        try {
            userDao.insert(user1);
        }catch (RuntimeException e){
            user1.setLogin("testUserDelme1");
            userDao.insert(user1);
        }

        List<User> allUsers=userDao.getAll();

        Group group=groupDao.createGroup("delme all users", allUsers);

        assertEquals(groupDao.getUsers(group).size(),allUsers.size());

        groupDao.removeById(group.getId());
        userDao.removeById(user1.getId());
    }

    @Test
    public void testChangeOwner() throws Exception {
        User user1=new User();
        user1.setLogin("testUserDelme");
        user1.setEmail("test@delme.usr");
        user1.setAddress("unknown address");
        user1.setPhoneNumber1("11111111111");
        user1.setPhoneNumber2("22222222222");
        user1.setComment("User for testing message DAO");
        user1.setSecondName("Userman");
        user1.setFirstName("Delme");
        user1.setPassword("123qwe");

        try {
            userDao.insert(user1);
        }catch (RuntimeException e){
            user1.setLogin("testUserDelme1");
            userDao.insert(user1);
        }

        groupDao.changeOwner(groupDao.findById(insertedIDs.get(0)),user1);

        assertEquals(groupDao.getByOwner(user1).size(),1);
        userDao.removeById(user1.getId());
    }

    @Test
    public void testAddUser() throws Exception {
        User user1=new User();
        user1.setLogin("testUserDelme");
        user1.setEmail("test@delme.usr");
        user1.setAddress("unknown address");
        user1.setPhoneNumber1("11111111111");
        user1.setPhoneNumber2("22222222222");
        user1.setComment("User for testing message DAO");
        user1.setSecondName("Userman");
        user1.setFirstName("Delme");
        user1.setPassword("123qwe");

        try {
            userDao.insert(user1);
        }catch (RuntimeException e){
            user1.setLogin("testUserDelme1");
            userDao.insert(user1);
        }

        groupDao.addUser(groupDao.findById(insertedIDs.get(0)),user1);
        assertEquals(groupDao.getUsers(groupDao.findById(insertedIDs.get(0))).size(),1);

        groupDao.removeUser(groupDao.findById(insertedIDs.get(0)), user1);
        assertEquals(groupDao.getUsers(groupDao.findById(insertedIDs.get(0))).size(),0);

        userDao.removeById(user1.getId());
    }
}