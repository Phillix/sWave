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
public class OrderTest {
    Order instance;
    public OrderTest() {
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
     * Test of getOrderId method, of class Order.
     */
    @Test
    public void testGetOrderId() {
        
        instance = new Order();
        int expResult = 0;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setOrderId method, of class Order.
     */
    @Test
    public void testSetOrderId() {
        
        int orderId = 1;
        instance = new Order();
        instance.setOrderId(orderId);
        assertEquals(orderId, instance.getOrderId());
    }

    /**
     * Test of getUserId method, of class Order.
     */
    @Test
    public void testGetUserId() {
        
        instance = new Order();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setUserId method, of class Order.
     */
    @Test
    public void testSetUserId() {
       
        int userId = 0;
        instance = new Order();
        instance.setUserId(userId);
        assertEquals(userId, instance.getUserId());
    }

    /**
     * Test of getDateOrdered method, of class Order.
     */
    @Test
    public void testGetDateOrdered() {
        instance = new Order();
        String expResult = "enter date";
        String result = instance.getDateOrdered();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setDateOrdered method, of class Order.
     */
    @Test
    public void testSetDateOrdered() {
        
        String dateOrdered = "new date";
        instance = new Order();
        instance.setDateOrdered(dateOrdered);
        assertEquals(dateOrdered, instance.getDateOrdered());
    }
    
    /**
     * Test of getOrderId method, of class Order.
     */
    @Test
    public void testGetTotal() {
        
        instance = new Order();
        double expResult = 0.0;
        double result = instance.getTotal();
        assertEquals(expResult, result,.2);
        
    }

    /**
     * Test of setOrderId method, of class Order.
     */
    @Test
    public void testSetTotal() {
        
        double total = 9.99;
        instance = new Order();
        instance.setTotal(total);
        assertEquals(total, instance.getTotal(),.2);
    }


    /**
     * Test of toString method, of class Order.
     */
    @Test
    public void testToString() {
        
        instance = new Order();
        String expResult = "Order{orderId=0, userId=0, dateOrdered=enter date, total=0.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
      
    }
    
}
