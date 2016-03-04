/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Merch;
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
    @Test
    public void testCreateMerch() {
        
        m = new Merch();
        int expResult = 0;
        int result = instance.createMerch(m);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of viewMerchAlpha method, of class MerchDao.
     */
    @Test
    public void testViewMerchAlpha() {
        
        ArrayList<Merch> merch = instance.viewMerchAlpha();
        assertEquals(new Merch(), merch.get(0));
    }
}
