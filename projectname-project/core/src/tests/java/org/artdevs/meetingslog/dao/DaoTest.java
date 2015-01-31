package org.artdevs.meetingslog.dao;

import junit.framework.TestCase;
import org.artdevs.meetingslog.core.dao.RoleDAO;
import org.artdevs.meetingslog.core.dao.UserDAO;
import org.artdevs.meetingslog.core.model.Role;
import org.artdevs.meetingslog.core.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {
                "classpath*:/META-INF/custom-config/*-custom-spring.xml",
                "classpath*:/META-INF/config/meetingslog-*-config.xml"
        }
)
public class DaoTest extends TestCase {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

     public void prepareData(final int testQuant){
        User testUser = new User();
        Role testRole = new Role();

        for(int i=0; i<testQuant; i++){

            testRole.setName("Slava"+i);
            testRole.setDescription("my"+i);
            testRole.setPermissions(0xffff+i);

            roleDAO.insert(testRole);
            testRole=roleDAO.findByName(testRole.getName());

            testUser.setLogin("testLogin"+i);
            testUser.setPassword("testPassword"+i);
            testUser.setFirstName("testFirstName"+i);
            testUser.setSecondName("testSecondName"+i);
            testUser.setEmail("test@email.none");
            testUser.setComment("testComment"+i);

            userDAO.insert(testUser);
        }
    }

    public void cleanData(final int testQuant){
        for(int i=0; i<testQuant; i++) {
            userDAO.removeByLogin("testLogin" + i);
            roleDAO.removeByName("Slava"+i);
        }

    }

    @Test
    public void testInsert ()throws Exception {
        User testUser = new User();

        Role testRole = new Role();

        testRole.setName("Slava");
        testRole.setDescription("my");
        testRole.setPermissions(0xffff);

        roleDAO.insert(testRole);

        testUser.setLogin("testLogin");
        testUser.setPassword("testPassword");
        testUser.setFirstName("testFirstName");
        testUser.setSecondName("testSecondName");
        testUser.setEmail("test@email.none");
        testUser.setComment("testComment");

        userDAO.insert(testUser);

        assertEquals(1, userDAO.getAll().size());

        userDAO.removeByLogin(testUser.getLogin());
        roleDAO.removeByName(testRole.getName());

        assertEquals(0, userDAO.getAll().size());
//        assertEquals(0,roleDAO.getAll().size());
    }

    @Test
    public void testGetAll() throws Exception {

        final int testQuant=10;

        prepareData(testQuant);

        assertEquals(testQuant,userDAO.getAll().size());

        cleanData(testQuant);

        assertEquals(0,userDAO.getAll().size());
//        assertEquals(0,roleDAO.getAll().size());
    }
    @Test
    public void testGetByEmail() throws Exception {

        final int testQuant=5;

        prepareData(testQuant);

        assertEquals(userDAO.getByEmail("test@email.none").size(), userDAO.getAll().size());

        User testUser=userDAO.findByLogin("testLogin"+(testQuant-1));

        testUser.setEmail("test@email.none5");
        userDAO.updateByLogin(testUser);

        assertEquals(userDAO.getByEmail("test@email.none").size(),userDAO.getAll().size()-1);

        cleanData(testQuant);

        assertEquals(0,userDAO.getByEmail("test@email.none").size());
//        assertEquals(0,roleDAO.getAll().size());
    }

    @Test
    public void testFindById() throws Exception {
        User testUser;
        final int testQuant=5;

        prepareData(testQuant);

        testUser=userDAO.findByLogin("testLogin2");

        assertEquals("testLogin2",userDAO.findById(testUser.getId()).getLogin());

        cleanData(testQuant);

//        assertEquals(0,roleDAO.getAll().size());
        assertEquals(0,userDAO.getByEmail("test@email.none").size());
    }

    @Test
    public void testFindByLogin() throws Exception {
        User testUser;
        final int testQuant=5;

        prepareData(testQuant);

        testUser=userDAO.findByLogin("testLogin2");

        assertEquals("testLogin2",testUser.getLogin());

        cleanData(testQuant);

        assertEquals(0,userDAO.getByEmail("test@email.none").size());
//        assertEquals(0,roleDAO.getAll().size());
    }

    @Test
    public void testUpdateById() throws Exception {
        User testUser;
        final int testQuant=5;

        prepareData(testQuant);

        testUser=userDAO.findByLogin("testLogin3");
        testUser.setPassword("123456");

        userDAO.updateById(testUser);

        assertEquals("123456", userDAO.findById(testUser.getId()).getPassword());

        cleanData(testQuant);

        assertEquals(0,userDAO.getByEmail("test@email.none").size());
//        assertEquals(0,roleDAO.getAll().size());
    }

    @Test
    public void testUpdateByLogin() throws Exception {
        User testUser = new User();
        final int testQuant=5;

        prepareData(testQuant);

        testUser=userDAO.findByLogin("testLogin3");
        testUser.setPassword("123456");

        userDAO.updateByLogin(testUser);

        assertEquals("123456", userDAO.findById(testUser.getId()).getPassword());

        cleanData(testQuant);

        assertEquals(0,userDAO.getByEmail("test@email.none").size());
//        assertEquals(0,roleDAO.getAll().size());
    }

    @Test
    public void testRemoveById() throws Exception {
        User testUser;
        final int testQuant=5;

        prepareData(testQuant);

        testUser=userDAO.findByLogin("testLogin3");

        userDAO.removeById(testUser.getId());

        assertEquals(null, userDAO.findByLogin("testLogin3"));

        cleanData(testQuant);

        assertEquals(0,userDAO.getByEmail("test@email.none").size());
//        assertEquals(0,roleDAO.getAll().size());

    }

    @Test
    public void testRemoveByLogin() throws Exception {
        User testUser;
        final int testQuant=5;

        prepareData(testQuant);

        testUser=userDAO.findByLogin("testLogin3");

        userDAO.removeByLogin(testUser.getLogin());

        assertEquals(null, userDAO.findById(testUser.getId()));

        cleanData(testQuant);

        assertEquals(0,userDAO.getByEmail("test@email.none").size());
//        assertEquals(0,roleDAO.getAll().size());
    }

    @Test
    public void testCheckPassword() throws Exception {
        User testUser;
        final int testQuant=5;

        prepareData(testQuant);

        int rand=(int)((Math.random()*100)%testQuant);

        assertEquals(true,userDAO.checkPassword("testLogin"+rand,"testPassword"+rand));
        assertEquals(false,userDAO.checkPassword("testLogin"+rand,"testPassword"+(rand+1)));

        cleanData(testQuant);

        assertEquals(0,userDAO.getByEmail("test@email.none").size());
//        assertEquals(0,roleDAO.getAll().size());
    }

}
