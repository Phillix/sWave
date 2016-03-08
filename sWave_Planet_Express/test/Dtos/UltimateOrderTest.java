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
public class UltimateOrderTest {
    
    UltimateOrder instance;
    public UltimateOrderTest() {
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
     * Test of getMerchSize method, of class UltimateOrder.
     */
    @Test
    public void testGetMerchSize() {
        
        instance = new UltimateOrder();
        int expResult = 0;
        int result = instance.getMerchSize();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of getMerchSize method, of class UltimateOrder.
     */
    @Test
    public void testGetSongSize() {
        
        instance = new UltimateOrder();
        int expResult = 0;
        int result = instance.getSongSize();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTotal method, of class UltimateOrder.
     */
    @Test
    public void testGetTotal() {
        instance = new UltimateOrder();
        double expResult = 50.0;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setTotal method, of class UltimateOrder.
     */
    @Test
    public void testSetTotal() {
        
        double total = 20.0;
        instance = new UltimateOrder();
        instance.setTotal(total);
        assertEquals(total,instance.getTotal(),.01);
    }

    /**
     * Test of getDateOrdered method, of class UltimateOrder.
     */
    @Test
    public void testGetDateOrdered() {
        
        instance = new UltimateOrder();
        String expResult = "date";
        String result = instance.getDateOrdered();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateOrdered method, of class UltimateOrder.
     */
    @Test
    public void testSetDateOrdered() {
        
        String dateOrdered = "new date";
        instance = new UltimateOrder();
        instance.setDateOrdered(dateOrdered);
        assertEquals(dateOrdered, instance.getDateOrdered());
    }

    /**
     * Test of getQty method, of class UltimateOrder.
     */
    @Test
    public void testGetQty() {
        
        instance = new UltimateOrder();
        int expResult = 0;
        int result = instance.getQty(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of setQty method, of class UltimateOrder.
     */
    @Test
    public void testSetQty() {
        
        int[] qty = new int[]{-1};
        instance = new UltimateOrder();
        instance.setQty(qty);
        assertEquals(qty[0],instance.getSongId(0));
    }
    
    /**
     * Test of getSongId method, of class UltimateOrder.
     */
    @Test
    public void testGetSongId() {
        
        instance = new UltimateOrder();
        int expResult = -1;
        int result = instance.getSongId(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of setSongId method, of class UltimateOrder.
     */
    @Test
    public void testSetSongId() {
        
        int[] id = new int[]{-7};
        instance = new UltimateOrder();
        instance.setSongId(id);
        assertEquals(id[0],instance.getSongId(0));
    }

    /**
     * Test of getMerchPrice method, of class UltimateOrder.
     */
    @Test
    public void testGetMerchPrice() {
        
        int i = 0;
        instance = new UltimateOrder();
        double expResult = 20.0;
        double result = instance.getMerchPrice(i);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setMerchPrice method, of class UltimateOrder.
     */
    @Test
    public void testSetMerchPrice() {
        
        double[] price = new double[]{9.99};
        instance = new UltimateOrder();
        instance.setMerchPrice(price);
        assertEquals(price[0],instance.getMerchPrice(0),0.01);
    }
    
    /**
     * Test of getSongPrice method, of class UltimateOrder.
     */
    @Test
    public void testGetSongPrice() {
        
        int i = 0;
        instance = new UltimateOrder();
        double expResult = 15.50;
        double result = instance.getSongPrice(i);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setSongPrice method, of class UltimateOrder.
     */
    @Test
    public void testSetSongPrice() {
        
        double[] price = new double[]{7.99};
        instance = new UltimateOrder();
        instance.setSongPrice(price);
        assertEquals(price[0],instance.getSongPrice(0),0.01);
    }

    /**
     * Test of getTitle method, of class UltimateOrder.
     */
    @Test
    public void testGetTitle() {
        
        int i = 0;
        instance = new UltimateOrder();
        String expResult = "testcase";
        String result = instance.getTitle(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class UltimateOrder.
     */
    @Test
    public void testSetTitle() {
        
        String[] title = new String[]{"newtitle"};
        instance = new UltimateOrder();
        instance.setTitle(title);
        assertEquals(title[0],instance.getTitle(0));
    }

   
}
