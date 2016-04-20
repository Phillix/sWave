/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Friend;
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
public class FriendDaoTest {
    static Friend f;
    static FriendDao instance;
    
    public FriendDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new FriendDao();
        f = new Friend(-1,-2);
        instance.removeFriend(-1,-2);
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
     * Test of requestFriend method, of class FriendDao.
     */
    @Test
    public void testRequestFriend() {
        int expResult = 0;
        int result = instance.requestFriend(f);
        assertEquals(expResult, result);
    }
    
//    /**
//     * Test of getPendingFriendRequests method, of class FriendDao.
//     */
//    @Test
//    public void testGetPendingFriendRequests() {
//        
//        int userId = -1;
//        ArrayList<Friend> result = instance.getPendingFriendRequests(userId);
//        System.out.println(result.get(0));
//    }  
    
    /**
     * Test of confirmFriend method, of class FriendDao.
     */
    @Test
    public void testConfirmFriend() {
        int userId = -1;
        int friendId = -2;
        int expResult = 0;
        int result = instance.confirmFriend(userId, friendId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserFriends method, of class FriendDao.
     */
    @Test
    public void testGetUserFriends() {
        
        int userId = -1;
        ArrayList<Friend> result = instance.getUserFriends(userId);
        System.out.println(result.get(0));
    }    
}
