package Daos;

import Dtos.User;
import Security.UserSecurity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author d00159732
 * @author AustinFoley96
 */
public class UsersDaoTest {
    static User u;
    static User u2;
    static UsersDao ud;
    static UserSecurity us = new UserSecurity();
    
    public UsersDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        u = new User("junit@junit.com", us.hash("Password123".toCharArray()), "junitsquad", "john", "unit", "1 junit way", "junit road", "junit city", "Mayo", false);
        u2 = new User("junit2@junit.com", us.hash("Password123".toCharArray()), "junit2squad", "john", "unit", "1 junit way", "junit road", "junit city", "Mayo", false);
        ud = new UsersDao();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ud.deleteUser(u.getEmail());
        ud.deleteUser(u2.getEmail());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkUname method, of class UsersDao.
     */
    @Test
    public void testCheckUnameValid() {
        int result = ud.checkUname(u2.getUsername());
        
        //Expecting the result to be -5, meaning the query returned OTHER, meaning it didn't find the username in the database
        assertEquals(-5, result);
    }
    
    /**
     * Test of checkUnameInvalid method, of class UsersDao.
     */
    @Test
    public void testCheckUnameInValid() {
        ud.register(u2);
        int result = ud.checkUname(u2.getUsername());
        
        //Expecting the result to be 0 as it will return SUCCESS because the username has been registered beforehand
        assertEquals(0, result);
    }

    /**
     * Test of register method, of class UsersDao.
     */
    @Test
    public void testRegister() {
        int expResult = 0;
        int result = ud.register(u);
        assertEquals(expResult, result);
    }

    /**
     * Test of logIn method, of class UsersDao.
     */
    @Test
    public void testLogIn() {
        ud.register(u);
        User u1 = ud.logIn(u.getEmail(), "Password123");
        assertEquals(true, u.equals(u1));
    }

    /**
     * Test of checkDetails method, of class UsersDao.
     */
    @Test
    public void testCheckDetailsValid() {
        ud.register(u);
        int result = ud.checkDetails("junit3@junit.com", "junit3squad");
        
        //Expecting it to be -5 as it will have returned OTHER because they don't exist
        assertEquals(-5, result);
    }
    
    /**
     * Test of checkDetails method, of class UsersDao.
     */
    @Test
    public void testCheckDetailsInValid() {
        ud.register(u);
        int result = ud.checkDetails("junit@junit.com", "junitsquad");
        
        //Expecting it to be 0 as it will have returned SUCCESS as they exist because we just registered those details
        assertEquals(0, result);
    }
    
    
}
