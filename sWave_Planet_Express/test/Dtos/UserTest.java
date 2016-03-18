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
 * @author Austin
 */
public class UserTest {
    User instance;
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
        instance = new User();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserId method being valid, of class Users.
     */
    @Test
    public void testGetUserIdValid() {
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUserId method being invalid, of class Users.
     */
    @Test
    public void testGetUserIdInvalid() {
        boolean expResult = false;
        boolean result = instance.getUserId() == 1;
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserId method being valid, of class Users.
     */
    @Test
    public void testSetUserIdValid() {
        int userId = 1;
        instance.setUserId(userId);
        assertEquals(userId, instance.getUserId());
    }
    
    /**
     * Test of setUserId method being invalid, of class Users.
     */
    @Test
    public void testSetUserIdInvalid() {
        boolean expResult = false;
        instance.setUserId(1);
        boolean result = instance.getUserId() == 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method being valid, of class Users.
     */
    @Test
    public void testGetEmailValid() {
        String expResult = "email";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getEmail method being invalid, of class Users.
     */
    @Test
    public void testGetEmailInvalid() {
        boolean expResult = false;
        boolean result = instance.getEmail().equals("ema1l");
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method being valid, of class Users.
     */
    @Test
    public void testSetEmailValid() {
        String email = "new";
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }
    
    /**
     * Test of setEmail method being invalid, of class Users.
     */
    @Test
    public void testSetEmailInvalid() {
        boolean expResult = false;
        instance.setEmail("new");
        boolean result = instance.getEmail().equals("email");
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method being valid, of class Users.
     */
    @Test
    public void testGetPasswordValid() {
        String expResult = "password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPassword method being invalid, of class Users.
     */
    @Test
    public void testGetPasswordInvalid() {
        boolean expResult = false;
        boolean result = instance.getPassword().equals("password1");
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method being valid, of class Users.
     */
    @Test
    public void testSetPasswordValid() {
        String password = "new";
        instance.setPassword(password);
        assertEquals(password, instance.getPassword());
    }
    
    /**
     * Test of setPassword method being invalid, of class Users.
     */
    @Test
    public void testSetPasswordInvalid() {
        boolean expResult = false;
        instance.setPassword("new");
        boolean result = instance.getPassword().equals("password");
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsername method being valid, of class Users.
     */
    @Test
    public void testGetUsernameValid() {
        String expResult = "username";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUsername method being invalid, of class Users.
     */
    @Test
    public void testGetUsernameInvalid() {
        boolean expResult = false;
        boolean result = instance.getUsername().equals("usernam3");
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method being valid, of class Users.
     */
    @Test
    public void testSetUsernameValid() {
        String username = "new";
        instance.setUsername(username);
        assertEquals(username, instance.getUsername());
    }
    
    /**
     * Test of setUsername method being invalid, of class Users.
     */
    @Test
    public void testSetUsernameInvalid() {
        boolean expResult = false;
        instance.setUsername("new");
        boolean result = instance.getUsername().equals("username");
        assertEquals(expResult, result);
    }

    /**
     * Test of getFname method being valid, of class Users.
     */
    @Test
    public void testGetFnameValid() {
        String expResult = "fname";
        String result = instance.getFname();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getFname method being invalid, of class Users.
     */
    @Test
    public void testGetFnameInvalid() {
        boolean expResult = false;
        boolean result = instance.getFname().equals("lname");
        assertEquals(expResult, result);
    }

    /**
     * Test of setFname method being valid, of class Users.
     */
    @Test
    public void testSetFnameValid() {
        String fname = "new";
        instance.setFname(fname);
        assertEquals(fname, instance.getFname());
    }
    
    /**
     * Test of setFname method being invalid, of class Users.
     */
    @Test
    public void testSetFnameInvalid() {
        boolean expResult = false;
        instance.setFname("new");
        boolean result = instance.getFname().equals("fname");
        assertEquals(expResult, result);
    }

    /**
     * Test of getLname method being valid, of class Users.
     */
    @Test
    public void testGetLnameValid() {
        String expResult = "lname";
        String result = instance.getLname();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLname method being invalid, of class Users.
     */
    @Test
    public void testGetLnameInvalid() {
        boolean expResult = false;
        boolean result = instance.getLname().equals("fname");
        assertEquals(expResult, result);
    }

    /**
     * Test of setLname method being valid, of class Users.
     */
    @Test
    public void testSetLnameValid() {
        String lname = "";
        instance.setLname(lname);
        assertEquals(lname, instance.getLname());
    }
    
    /**
     * Test of setLname method being invalid, of class Users.
     */
    @Test
    public void testSetLnameInvalid() {
        boolean expResult = false;
        instance.setLname("fname");
        boolean result = instance.getLname().equals("lname");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdd1 method being valid, of class Users.
     */
    @Test
    public void testGetAdd1Valid() {
        String expResult = "add1";
        String result = instance.getAdd1();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAdd1 method being invalid, of class Users.
     */
    @Test
    public void testGetAdd1Invalid() {
        boolean expResult = false;
        boolean result = instance.getAdd1().equals("add2");
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdd1 method being valid, of class Users.
     */
    @Test
    public void testSetAdd1Valid() {
        String add1 = "new";
        instance.setAdd1(add1);
        assertEquals(add1, instance.getAdd1());
    }
    
    /**
     * Test of setAdd1 method being invalid, of class Users.
     */
    @Test
    public void testSetAdd1Invalid() {
        boolean expResult = false;
        instance.setAdd1("new");
        boolean result = instance.getAdd1().equals("add1");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdd2 method being valid, of class Users.
     */
    @Test
    public void testGetAdd2Valid() {
        String expResult = "add2";
        String result = instance.getAdd2();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAdd2 method being invalid, of class Users.
     */
    @Test
    public void testGetAdd2Invalid() {
        boolean expResult = false;
        boolean result = instance.getAdd2().equals("add1");
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdd2 method being valid, of class Users.
     */
    @Test
    public void testSetAdd2Valid() {
        String add2 = "new";
        instance.setAdd2(add2);
        assertEquals(add2, instance.getAdd2());
    }
    
    /**
     * Test of setAdd2 method being invalid, of class Users.
     */
    @Test
    public void testSetAdd2Invalid() {
        boolean expResult = false;
        instance.setAdd2("new");
        boolean result = instance.getAdd2().equals("add2");
        assertEquals(expResult, result);
    }

    /**
     * Test of getCity method being valid, of class Users.
     */
    @Test
    public void testGetCityValid() {
        String expResult = "city";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCity method being invalid, of class Users.
     */
    @Test
    public void testGetCityInvalid() {
        boolean expResult = false;
        boolean result = instance.getCity().equals("sity");
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method being valid, of class Users.
     */
    @Test
    public void testSetCityValid() {
        String city = "new";
        instance.setCity(city);
        assertEquals(city, instance.getCity());
    }
    
    /**
     * Test of setCity method being invalid, of class Users.
     */
    @Test
    public void testSetCityInvalid() {
        boolean expResult = false;
        instance.setCity("new");
        boolean result = instance.getCity().equals("city");
        assertEquals(expResult, result);
    }

    /**
     * Test of getCounty method being valid, of class Users.
     */
    @Test
    public void testGetCountyValid() {
        String expResult = "CN";
        String result = instance.getCounty();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCounty method being invalid, of class Users.
     */
    @Test
    public void testGetCountyInvalid() {
        boolean expResult = false;
        boolean result = instance.getCounty().equals("country");
        assertEquals(expResult, result);
    }

    /**
     * Test of setCounty method being valid, of class Users.
     */
    @Test
    public void testSetCountyValid() {
        String county = "new";
        instance.setCounty(county);
        assertEquals(county, instance.getCounty());
    }
    
    /**
     * Test of setCounty method being invalid, of class Users.
     */
    @Test
    public void testSetCountyInvalid() {
        boolean expResult = false;
        instance.setCounty("new");
        boolean result = instance.getCounty().equals("county");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSkin method being valid, of class Users.
     */
    @Test
    public void testGetSkinValid() {
        String expResult = "swave";
        String result = instance.getSkin();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSkin method being invalid, of class Users.
     */
    @Test
    public void testGetSkinInvalid() {
        boolean expResult = false;
        boolean result = instance.getSkin().equals("quantum");
        assertEquals(expResult, result);
    }

    /**
     * Test of setSkin method being valid, of class Users.
     */
    @Test
    public void testSetSkinValid() {
        String skin = "quantum";
        instance.setSkin(skin);
        assertEquals(skin, instance.getSkin());
    }
    
    /**
     * Test of setSkin method being invalid, of class Users.
     */
    @Test
    public void testSetSkinInvalid() {
        boolean expResult = false;
        instance.setSkin("quantum");
        boolean result = instance.getSkin().equals("swave");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isIsAdmin method being valid, of class Users.
     */
    @Test
    public void testIsIsAdminValid() {
        boolean expResult = false;
        boolean result = instance.isIsAdmin();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isIsAdmin method being invalid, of class Users.
     */
    @Test
    public void testIsIsAdminInvalid() {
        boolean expResult = false;
        boolean result = instance.isIsAdmin() == true;
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsAdmin method being valid, of class Users.
     */
    @Test
    public void testSetIsAdminValid() {
        boolean isAdmin = true;
        instance.setIsAdmin(isAdmin);
        assertEquals(isAdmin, instance.isIsAdmin());
    }
    
    /**
     * Test of setIsAdmin method being invalid, of class Users.
     */
    @Test
    public void testSetIsAdminInvalid() {
        boolean expResult = false;
        instance.setIsAdmin(true);
        boolean result = instance.isIsAdmin() == false;
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method being valid, of class Users.
     */
    @Test
    public void testToStringValid() {
        String expResult = "User{userId=0, email=email, password=password, username=username, fname=fname, lname=lname, add1=add1, add2=add2, city=city, county=CN, skin=swave, isAdmin=false}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method being invalid, of class Users.
     */
    @Test
    public void testToStringInvalid() {
        boolean expResult = false;
        boolean result = instance.toString().equals("UseruserId=0, email=email, password=password, username=username, fname=fname, lname=lname, add1=add1, add2=add2, city=city, county=county, skin=swave, isAdmin=false");
        assertEquals(expResult, result);
    }
    
}
