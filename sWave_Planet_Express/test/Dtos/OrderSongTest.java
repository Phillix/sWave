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
public class OrderSongTest {
    
    OrderSong instance;
    public OrderSongTest() {
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
     * Test of getOrderId method, of class OrderSong.
     */
    @Test
    public void testGetOrderId() {
        
        instance = new OrderSong();
        int expResult = -1;
        int result = instance.getOrderId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderId method, of class OrderSong.
     */
    @Test
    public void testSetOrderId() {
        
        int orderId = 0;
        OrderSong instance = new OrderSong();
        instance.setOrderId(orderId);
        assertEquals(orderId, instance.getOrderId());
    }

    /**
     * Test of getSongId method, of class OrderSong.
     */
    @Test
    public void testGetSongId() {
        
        instance = new OrderSong();
        int expResult = -1;
        int result = instance.getSongId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSongId method, of class OrderSong.
     */
    @Test
    public void testSetSongId() {
        
        int songId = 0;
        OrderSong instance = new OrderSong();
        instance.setSongId(songId);
        assertEquals(songId, instance.getSongId());
    }

    /**
     * Test of getPricePaid method, of class OrderSong.
     */
    @Test
    public void testGetPricePaid() {
        
        instance = new OrderSong();
        double expResult = 20.0;
        double result = instance.getPricePaid();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPricePaid method, of class OrderSong.
     */
    @Test
    public void testSetPricePaid() {
        
        double pricePaid = 0.0;
        instance = new OrderSong();
        instance.setPricePaid(pricePaid);
        assertEquals(pricePaid, instance.getPricePaid(),0.01);
    }

    /**
     * Test of toString method, of class OrderSong.
     */
    @Test
    public void testToString() {
        
        instance = new OrderSong();
        String expResult = "OrderSong{orderId=-1, songId=-1, pricePaid=20.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
