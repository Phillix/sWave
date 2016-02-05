/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Dtos.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Phillix
 */
public class UsersTest {
    
    public UsersTest() {
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
     * Test of getUserId method, of class Users.
     */
    @Test
    public void testGetUserId() {
        
        Users instance = new Users();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserId method, of class Users.
     */
    @Test
    public void testSetUserId() {
        
        int userId = 0;
        Users instance = new Users();
        instance.setUserId(userId);
        assertEquals(userId, instance.getUserId());
    }

    /**
     * Test of getEmail method, of class Users.
     */
    @Test
    public void testGetEmail() {
        
        Users instance = new Users();
        String expResult = "email";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Users.
     */
    @Test
    public void testSetEmail() {
        
        String email = "new";
        Users instance = new Users();
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getPassword method, of class Users.
     */
    @Test
    public void testGetPassword() {
        
        Users instance = new Users();
        String expResult = "password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setPassword method, of class Users.
     */
    @Test
    public void testSetPassword() {
        
        String password = "new";
        Users instance = new Users();
        instance.setPassword(password);
        assertEquals(password, instance.getPassword());
    }

    /**
     * Test of getUsername method, of class Users.
     */
    @Test
    public void testGetUsername() {
        
        Users instance = new Users();
        String expResult = "username";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class Users.
     */
    @Test
    public void testSetUsername() {
        
        String username = "new";
        Users instance = new Users();
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }

    /**
     * Test of getFname method, of class Users.
     */
    @Test
    public void testGetFname() {
        
        Users instance = new Users();
        String expResult = "fname";
        String result = instance.getFname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFname method, of class Users.
     */
    @Test
    public void testSetFname() {
        
        String fname = "new";
        Users instance = new Users();
        instance.setFname(fname);
        assertEquals(fname, instance.getFname());
    }

    /**
     * Test of getLname method, of class Users.
     */
    @Test
    public void testGetLname() {
        
        Users instance = new Users();
        String expResult = "lname";
        String result = instance.getLname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLname method, of class Users.
     */
    @Test
    public void testSetLname() {
        
        String lname = "";
        Users instance = new Users();
        instance.setLname(lname);
        assertEquals(lname, instance.getLname());
    }

    /**
     * Test of getAdd1 method, of class Users.
     */
    @Test
    public void testGetAdd1() {
        
        Users instance = new Users();
        String expResult = "add1";
        String result = instance.getAdd1();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdd1 method, of class Users.
     */
    @Test
    public void testSetAdd1() {
       
        String add1 = "new";
        Users instance = new Users();
        instance.setAdd1(add1);
        assertEquals(add1, instance.getAdd1());
    }

    /**
     * Test of getAdd2 method, of class Users.
     */
    @Test
    public void testGetAdd2() {
        
        Users instance = new Users();
        String expResult = "add2";
        String result = instance.getAdd2();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAdd2 method, of class Users.
     */
    @Test
    public void testSetAdd2() {
        
        String add2 = "new";
        Users instance = new Users();
        instance.setAdd2(add2);
        assertEquals(add2, instance.getAdd2());
    }

    /**
     * Test of getCity method, of class Users.
     */
    @Test
    public void testGetCity() {
        
        Users instance = new Users();
        String expResult = "city";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class Users.
     */
    @Test
    public void testSetCity() {
        
        String city = "new";
        Users instance = new Users();
        instance.setCity(city);
        assertEquals(city, instance.getCity());
    }

    /**
     * Test of getCounty method, of class Users.
     */
    @Test
    public void testGetCounty() {
        
        Users instance = new Users();
        String expResult = "county";
        String result = instance.getCounty();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCounty method, of class Users.
     */
    @Test
    public void testSetCounty() {
        
        String county = "new";
        Users instance = new Users();
        instance.setCounty(county);
        assertEquals(county, instance.getCounty());
    }

    /**
     * Test of isIsAdmin method, of class Users.
     */
    @Test
    public void testIsIsAdmin() {
        
        Users instance = new Users();
        boolean expResult = false;
        boolean result = instance.isIsAdmin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsAdmin method, of class Users.
     */
    @Test
    public void testSetIsAdmin() {
        
        boolean isAdmin = true;
        Users instance = new Users();
        instance.setIsAdmin(isAdmin);
        assertEquals(isAdmin, instance.isIsAdmin());
    }

    /**
     * Test of toString method, of class Users.
     */
    @Test
    public void testToString() {
        
        Users instance = new Users();
        String expResult = "Users{userId=0, email=email, password=password, username=username, fname=fname, lname=lname, add1=add1, add2=add2, salt=salt, city=city, county=county, isAdmin=false}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
