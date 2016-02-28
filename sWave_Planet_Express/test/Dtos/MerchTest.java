/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class MerchTest {
    Merch instance;
    public MerchTest() {
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
     * Test of getMerchId method, of class Merch.
     */
    @Test
    public void testGetMerchId() {
        
        instance = new Merch();
        int expResult = 0;
        int result = instance.getMerchId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMerchId method, of class Merch.
     */
    @Test
    public void testSetMerchId() {
        
        int merchId = 1;
        instance = new Merch();
        instance.setMerchId(merchId);
        assertEquals(merchId, instance.getMerchId());
    }

    /**
     * Test of getTitle method, of class Merch.
     */
    @Test
    public void testGetTitle() {
        
        instance = new Merch();
        String expResult = "default";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Merch.
     */
    @Test
    public void testSetTitle() {
        
        String title = "new title";
        instance = new Merch();
        instance.setTitle(title);
        assertEquals(title, instance.getTitle());
    }

    /**
     * Test of getPrice method, of class Merch.
     */
    @Test
    public void testGetPrice() {
        
        Merch instance = new Merch();
        double expResult = 9.99;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setPrice method, of class Merch.
     */
    @Test
    public void testSetPrice() {
        
        double price = 7.0;
        Merch instance = new Merch();
        instance.setPrice(price);
         assertEquals(price, instance.getPrice(),.2);
    }

    /**
     * Test of toString method, of class Merch.
     */
    @Test
    public void testToString() {
        
        Merch instance = new Merch();
        String expResult = "Merch{merchId=0, title=default, price=9.99}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class Merch.
     */
    @Test
    public void testEquals() {
        
        instance = new Merch();
        Merch instance2 = new Merch();
        boolean expResult = true;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    
}
