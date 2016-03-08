/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Merch;
import Dtos.OrderMerch;
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
public class MerchDaoTest {
    
    Merch m;
    MerchDao instance;
    public MerchDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new MerchDao();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createMerch method, of class MerchDao.
     */
//    @Test
//    public void testCreateMerch() {
//        
//        m = new Merch();
//        int expResult = 0;
//        int result = instance.createMerch(m);
//        assertEquals(expResult, result);
//    }
    
    /**
     * Test of viewMerchAlpha method, of class MerchDao.
     */
    @Test
    public void testViewMerchAlpha() {
        
        ArrayList<Merch> merch = instance.viewMerchAlpha();
        Merch m = new Merch("merch 1", 5.50);
        m.setMerchId(-1);
        assertEquals(m, merch.get(0));
    }
    
    /**
     * Test of getMerchInOrder method, of class MerchDao.
     */
    @Test
    public void testGetMerchInOrder() {
        
        instance = new MerchDao();
        ArrayList<OrderMerch> orderMerch = new ArrayList<OrderMerch>();
        OrderMerch om = new OrderMerch(-1,-1,1,5.50);
        orderMerch.add(om);
        ArrayList<Merch> merchList = instance.getMerchInOrder(orderMerch);
        
        if(merchList != null) {
            System.out.println(merchList.get(0));
        }
    }
}
