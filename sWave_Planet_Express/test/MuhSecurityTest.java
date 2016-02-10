/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazeclasses;

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
public class MuhSecurityTest {
    
    public MuhSecurityTest() {
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
     * Test of hash method, of class MuhSecurity.
     */
    @Test
    public void testHash() throws Exception {
       
        String password = "password";
        MuhSecurity instance = new MuhSecurity();
        String hashed = instance.hash(password.toCharArray());
        boolean expResult = false;
        boolean result = hashed.equalsIgnoreCase(password);
        assertEquals(expResult, result);
    }

     /**
     * Test of hash method, of class MuhSecurity.
     */
    @Test
    public void testHash2() throws Exception {
       
        String password = "password";
        MuhSecurity instance = new MuhSecurity();
        String hashed = instance.hash(password.toCharArray());
        String hashed2 = instance.hash(password.toCharArray());
        boolean expResult = false;
        boolean result = hashed.equalsIgnoreCase(hashed2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validatePassword method, of class MuhSecurity.
     */
    @Test
    public void testValidatePassword() throws Exception {
        
        MuhSecurity ms = new MuhSecurity();
        String password = "password";
        String correctHash = ms.hash(password.toCharArray());
        boolean expResult = true;
        boolean result = ms.checkPassword(password.toCharArray(), correctHash);
        assertEquals(expResult, result);
    }
    
}
