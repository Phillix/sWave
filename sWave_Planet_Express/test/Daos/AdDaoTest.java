/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Ad;
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
public class AdDaoTest {
    
    public AdDaoTest() {
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
     * Test of getAd method, of class AdDao.
     */
    @Test
    public void testGetAd() {
        
        int id = 1;
        AdDao instance = new AdDao();
        String result = instance.getAd(id).toString();
        String expResult = "Ad{adId=1, adUrl=/testData}";
        assertEquals(expResult, result);
    }
    
}
