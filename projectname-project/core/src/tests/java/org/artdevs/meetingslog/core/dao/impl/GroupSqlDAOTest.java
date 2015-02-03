package org.artdevs.meetingslog.core.dao.impl;

import junit.framework.TestCase;
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
    GroupSqlDAO groupSqlDao;

    @Autowired
    UserSqlDao userSqlDao;

    int   numInitGrp;//messageSqlDao.getAll()==null?0:messageSqlDao.getAll().size();
    int   numTestGrp;
    User user;
    List<Integer> insertedIDs=new ArrayList<>(numTestGrp);

    @Before
    public void setUp() throws Exception {
        //super.setUp();
        numInitGrp=groupSqlDao.getAll()==null?0:groupSqlDao.getAll().size();
        numTestGrp=10;
        user=userSqlDao.findByLogin("admin");

        if(user==null) throw (new Exception("Failed to get default user (admin)"));

        Group grp=new Group();
        for(int i=0; i<numTestGrp; i++){
            grp.setName("Test group " + i);
            grp.setOwner_id(user.getId());

            groupSqlDao.insert(grp);
            insertedIDs.add(grp.getId());
        }
    }

    @After
    public void tearDown() throws Exception {
        for (Integer i:insertedIDs){
            groupSqlDao.removeById(i);
        }
    }

    @Test
    public void testGetAll() throws Exception {
        assertEquals(numTestGrp+numInitGrp,groupSqlDao.getAll().size());
    }

    @Test
    public void testGetByUser() throws Exception {
        assertTrue(groupSqlDao.getByUser(user).size()>=numTestGrp &&
                groupSqlDao.getByUser(user).size()<=groupSqlDao.getAll().size());
    }

    @Test
    public void testFindById() throws Exception {
        Random random=new Random(numTestGrp);
        Group grp=groupSqlDao.findById(insertedIDs.get(random.nextInt()%numTestGrp));
        assertTrue(grp.getName().contains("Test group"));
    }

    @Test
    public void testInsert() throws Exception {
        Group grp=new Group();
        grp.setName("Test group " + (numTestGrp));
        grp.setOwner_id(user.getId());

        groupSqlDao.insert(grp);
        insertedIDs.add(grp.getId());

        assertEquals(groupSqlDao.findById(insertedIDs.get(numTestGrp)).getName(),
                ("Test group "+numTestGrp));

    }

    @Test
    public void testUpdateById() throws Exception {
        final String newGrpText="Corrected group name.";

        Group grp=groupSqlDao.findById(insertedIDs.get(0));
        grp.setName(newGrpText);
        groupSqlDao.updateById(grp);

        assertEquals(newGrpText,groupSqlDao.findById(insertedIDs.get(0)).getName());

    }

    @Test
    public void testRemoveById() throws Exception {
        groupSqlDao.removeById(insertedIDs.get(numTestGrp-1));

        assertNull(groupSqlDao.findById(insertedIDs.get(numTestGrp - 1)));

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
            userSqlDao.insert(user1);
        }catch (RuntimeException e){
            user1.setLogin("testUserDelme1");
            userSqlDao.insert(user1);
        }

        for (Integer i:insertedIDs){
            Group grp=groupSqlDao.findById(i);
            grp.setOwner_id(user1.getId());
            groupSqlDao.updateById(grp);
        }

        groupSqlDao.removeByUser(user1);
        userSqlDao.removeById(user1.getId());

        assertEquals(numInitGrp, groupSqlDao.getAll().size());

    }
}