package Dtos;

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
public class UserTest {
    
    public UserTest() {
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
        
        User instance = new User();
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
        User instance = new User();
        instance.setUserId(userId);
        assertEquals(userId, instance.getUserId());
    }

    /**
     * Test of getEmail method, of class Users.
     */
    @Test
    public void testGetEmail() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getPassword method, of class Users.
     */
    @Test
    public void testGetPassword() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setPassword(password);
        assertEquals(password, instance.getPassword());
    }

    /**
     * Test of getUsername method, of class Users.
     */
    @Test
    public void testGetUsername() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }

    /**
     * Test of getFname method, of class Users.
     */
    @Test
    public void testGetFname() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setFname(fname);
        assertEquals(fname, instance.getFname());
    }

    /**
     * Test of getLname method, of class Users.
     */
    @Test
    public void testGetLname() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setLname(lname);
        assertEquals(lname, instance.getLname());
    }

    /**
     * Test of getAdd1 method, of class Users.
     */
    @Test
    public void testGetAdd1() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setAdd1(add1);
        assertEquals(add1, instance.getAdd1());
    }

    /**
     * Test of getAdd2 method, of class Users.
     */
    @Test
    public void testGetAdd2() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setAdd2(add2);
        assertEquals(add2, instance.getAdd2());
    }

    /**
     * Test of getCity method, of class Users.
     */
    @Test
    public void testGetCity() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setCity(city);
        assertEquals(city, instance.getCity());
    }

    /**
     * Test of getCounty method, of class Users.
     */
    @Test
    public void testGetCounty() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setCounty(county);
        assertEquals(county, instance.getCounty());
    }
    
    /**
     * Test of getCounty method, of class Users.
     */
    @Test
    public void testGetSkin() {
        
        User instance = new User();
        String expResult = "flat";
        String result = instance.getSkin();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCounty method, of class Users.
     */
    @Test
    public void testSetSkin() {
        
        String skin = "quantum";
        User instance = new User();
        instance.setSkin(skin);
        assertEquals(skin, instance.getSkin());
    }

    /**
     * Test of isIsAdmin method, of class Users.
     */
    @Test
    public void testIsIsAdmin() {
        
        User instance = new User();
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
        User instance = new User();
        instance.setIsAdmin(isAdmin);
        assertEquals(isAdmin, instance.isIsAdmin());
    }

    /**
     * Test of toString method, of class Users.
     */
    @Test
    public void testToString() {
        
        User instance = new User();
        String expResult = "User{userId=0, email=email, password=password, username=username, fname=fname, lname=lname, add1=add1, add2=add2, city=city, county=county, skin=flat, isAdmin=false}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
