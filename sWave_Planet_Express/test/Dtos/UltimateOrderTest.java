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
     * Test of getSize method, of class UltimateOrder.
     */
    @Test
    public void testGetSize() {
        
        instance = new UltimateOrder();
        int expResult = 0;
        int result = instance.getSize();
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
        
        int i = 0;
        UltimateOrder instance = new UltimateOrder();
        int expResult = 0;
        int result = instance.getQty(i);
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
        assertEquals(qty[0],instance.getQty(0));
    }

    /**
     * Test of getPrice method, of class UltimateOrder.
     */
    @Test
    public void testGetPrice() {
        
        int i = 0;
        instance = new UltimateOrder();
        double expResult = 20.0;
        double result = instance.getPrice(i);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class UltimateOrder.
     */
    @Test
    public void testSetPrice() {
        
        double[] price = new double[]{9.99};
        instance = new UltimateOrder();
        instance.setPrice(price);
        assertEquals(price[0],instance.getPrice(0),0.01);
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
