/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.OrderSong;
import java.util.ArrayList;
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
public class OrderSongDaoTest {
    
    OrderSongDao instance;
    public OrderSongDaoTest() {
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
     * Test of createOrderSong method, of class OrderSongDao.
     */
    @Test
    public void testCreateOrderSong() {
        
        OrderSong os = new OrderSong();
        instance = new OrderSongDao();
        int expResult = 0;
        int result = instance.createOrderSong(os);
        assertEquals(expResult, result);
    }
    
    /**
    * Test of getOrderSongInOrder method, of class OrderSongDao.
    */
    @Test
    public void testGetOrderSongInOrder() {
        
        instance = new OrderSongDao();
        ArrayList<OrderSong> os = instance.getOrderSongInOrder(-1);
        for(OrderSong o : os) {
            System.out.println(o);
        }
    }
}
