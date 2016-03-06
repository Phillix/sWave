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
public class OrderMerchTest {
    
    OrderMerch instance;
    public OrderMerchTest() {
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
     * Test of getOrderId method, of class OrderMerch.
     */
    @Test
    public void testGetOrderId() {
        
        instance = new OrderMerch();
        int expResult = -1;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderId method, of class OrderMerch.
     */
    @Test
    public void testSetOrderId() {
        
        int orderId = 0;
        instance = new OrderMerch();
        instance.setOrderId(orderId);
        assertEquals(orderId, instance.getOrderId());
    }

    /**
     * Test of getMerchId method, of class OrderMerch.
     */
    @Test
    public void testGetMerchId() {
        
        instance = new OrderMerch();
        int expResult = -1;
        int result = instance.getMerchId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMerchId method, of class OrderMerch.
     */
    @Test
    public void testSetMerchId() {
        
        int merchId = 0;
        instance = new OrderMerch();
        instance.setMerchId(merchId);
        assertEquals(merchId, instance.getMerchId());
    }

    /**
     * Test of getQty method, of class OrderMerch.
     */
    @Test
    public void testGetQty() {
       
        instance = new OrderMerch();
        int expResult = -1;
        int result = instance.getQty();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQty method, of class OrderMerch.
     */
    @Test
    public void testSetQty() {
        
        int qty = 0;
        instance = new OrderMerch();
        instance.setQty(qty);
        assertEquals(qty, instance.getQty());
    }

    /**
     * Test of getPricePaid method, of class OrderMerch.
     */
    @Test
    public void testGetPricePaid() {
        
        instance = new OrderMerch();
        double expResult = -9.99;
        double result = instance.getPricePaid();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setPricePaid method, of class OrderMerch.
     */
    @Test
    public void testSetPricePaid() {
        
        double pricePaid = 0.0;
        instance = new OrderMerch();
        instance.setPricePaid(pricePaid);
        assertEquals(pricePaid, instance.getPricePaid(),.01);
    }

    /**
     * Test of toString method, of class OrderMerch.
     */
    @Test
    public void testToString() {
        
        instance = new OrderMerch();
        String expResult = "OrderMerch{orderId=-1, merchId=-1, qty=-1, pricePaid=-9.99}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
