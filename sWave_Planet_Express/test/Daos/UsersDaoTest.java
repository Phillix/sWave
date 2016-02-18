/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author d00159732
 */
public class UsersDaoTest {
    
    public UsersDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkUname method, of class UsersDao.
     */
    @Test
    public void testCheckUname() {
        System.out.println("checkUname");
        String username = "";
        UsersDao instance = new UsersDao();
        int expResult = 0;
        int result = instance.checkUname(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class UsersDao.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        User u = null;
        UsersDao instance = new UsersDao();
        int expResult = 0;
        int result = instance.register(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logIn method, of class UsersDao.
     */
    @Test
    public void testLogIn() {
        System.out.println("logIn");
        String email = "";
        String password = "";
        UsersDao instance = new UsersDao();
        User expResult = null;
        User result = instance.logIn(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDetails method, of class UsersDao.
     */
    @Test
    public void testCheckDetails() {
        System.out.println("checkDetails");
        String email = "";
        String username = "";
        UsersDao instance = new UsersDao();
        int expResult = 0;
        int result = instance.checkDetails(email, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
