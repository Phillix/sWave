package Security;

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
public class UserSecurityTest {
    
    public UserSecurityTest() {
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
     * Test of hash method, of class UserSecurity.
     */
    @Test
    public void testHash() throws Exception {
       
        String password = "password";
        UserSecurity instance = new UserSecurity();
        String hashed = instance.hash(password.toCharArray());
        boolean expResult = false;
        boolean result = hashed.equalsIgnoreCase(password);
        assertEquals(expResult, result);
    }

     /**
     * Test of hash method, of class UserSecurity.
     */
    @Test
    public void testHash2() throws Exception {
       
        String password = "password";
        UserSecurity instance = new UserSecurity();
        String hashed = instance.hash(password.toCharArray());
        String hashed2 = instance.hash(password.toCharArray());
        boolean expResult = false;
        boolean result = hashed.equalsIgnoreCase(hashed2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validatePassword method, of class UserSecurity.
     */
    @Test
    public void testValidatePassword() throws Exception {
        
        UserSecurity ms = new UserSecurity();
        String password = "password";
        String correctHash = ms.hash(password.toCharArray());
        boolean expResult = true;
        boolean result = ms.checkPassword(password.toCharArray(), correctHash);
        assertEquals(expResult, result);
    }
    
}
