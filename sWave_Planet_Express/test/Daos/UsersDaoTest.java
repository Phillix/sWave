package Daos;

import Dtos.User;
import Security.UserSecurity;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phillix
 * @author Austin
 */
public class UsersDaoTest {
    User u;
    static UsersDao ud;
    static UserSecurity us = new UserSecurity();
    
    public UsersDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        ud = new UsersDao();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        u = new User("ceo@banana.com", us.hash("password".toCharArray()), "appelman", "Steev", "Jubs", "1 hello", "Some Street", "New Yorko", "Cavan", "nova", false);
        u.setUserId(-1);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkUname method, of class UsersDao.
     */
    @Test
    public void testCheckUnameValid() {
        int result = ud.checkUname(u.getUsername());
        boolean expResult = true;
        boolean accResult = result == 0;
        //Expecting the result to be -5, meaning the query returned OTHER, meaning it didn't find the username in the database
        assertEquals(expResult, accResult);
    }
    
    /**
     * Test of checkUnameInvalid method, of class UsersDao.
     */
    @Test
    public void testCheckUnameInValid() {
        int result = ud.checkUname(u.getUsername() + " dddd");
        boolean expResult = true;
        boolean accResult = result == -5;
        //Expecting the result to be -5, meaning the query returned OTHER, meaning it didn't find the username in the database
        assertEquals(expResult, accResult);
    }

    /**
     * Test of register method, of class UsersDao.
     */
    @Test
    public void testRegister() {
        User u1 = new User();
        int expResult = 0;
        int result = ud.register(u1);
        ud.deleteUser(u1.getEmail());
        assertEquals(expResult, result);
    }

    /**
     * Test of logIn method, of class UsersDao.
     */
    @Test
    public void testLogIn() {
        User u1 = ud.logIn(u.getEmail(), "password");
        assertEquals(true, u.equals(u1));
    }

    /**
     * Test of checkDetails method, of class UsersDao.
     */
    @Test
    public void testCheckDetailsInvalid() {
        int result = ud.checkDetails("junit1@junit.com", "junit1squad");
        
        //Expecting it to be -5 as it will have returned OTHER because they don't exist
        assertEquals(-5, result);
    }
    
    /**
     * Test of checkDetails method, of class UsersDao.
     */
    @Test
    public void testCheckDetailsValid() {
        int result = ud.checkDetails(u.getEmail(), u.getUsername());
        
        //Expecting it to be 0 as it will have returned SUCCESS as they exist because we just registered those details
        assertEquals(0, result);
    }
    
    /**
     * Test of getUserById method, of class UsersDao.
     */
    @Test
    public void testGetUserById() {
        User uidTest = ud.getUserById(-1);
        String result = uidTest.getUsername();
        String expected = "appelman";
        assertEquals(expected, result);
    }

    /**
     * Test of getUserId method, of class UsersDao.
     */
    @Test
    public void testGetUserId() {
        String email = u.getEmail();
        String username = u.getUsername();
        int expResult = -1;
        int result = ud.getUserId(email, username);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteUser method, of class UsersDao.
     */
    @Test
    public void testDeleteUser() {
        User u1 = new User();
        ud.register(u1);
        int expResult = 0;
        int result = ud.deleteUser(u1.getEmail());
        assertEquals(expResult, result);
    }

    /**
     * Test of changeSkin method, of class UsersDao.
     */
    @Test
    public void testChangeSkin() {
        String skin = "flat";
        int userid = -1;
        int expResult = 0;
        int result = ud.changeSkin(skin, userid);
        assertEquals(expResult, result);
    }
}
