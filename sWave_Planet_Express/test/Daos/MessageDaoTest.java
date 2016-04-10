/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Message;
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
public class MessageDaoTest {
    static Message m;
    static MessageDao instance;
    
    public MessageDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new MessageDao();
    }
    
    @AfterClass
    public static void tearDownClass() {
        instance.deleteConversation(-2);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createMsg method, of class MessageDao.
     */
    @Test
    public void testCreateMsg() {
        m = new Message();
        int expResult = 0;
        int result = instance.createMsg(m);
        assertEquals(expResult, result);
    }

    /**
     * Test of getConversation method, of class MessageDao.
     */
    @Test
    public void testGetConversation() {
        int friendId = -2;
        ArrayList<Message> result = instance.getConversation(friendId);
        System.out.println(result.get(0));
    }

    /**
     * Test of markAsRead method, of class MessageDao.
     */
//    @Test
//    public void testMarkAsRead() {
//        int msgId = -1;
//        boolean expResult = true;
//        instance.markAsRead(msgId);
//        int friendId = -2;
//        ArrayList<Message> result = instance.getConversation(friendId);
//        assertEquals(expResult, result.get(0).getStatus());
//        System.out.println(result.get(0));
//    }  
}
