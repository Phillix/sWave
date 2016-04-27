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
 * Class for testing MessageDao methods, for valid and invalid
 * @author Austin
 * @author Phillix
 */
public class MessageDaoTest {
    static MyDataSource ds = new MyDataSource();
    static Message m;
    static MessageDao instance;
    
    public MessageDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new MessageDao(ds);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        m = new Message();
        instance.createMsg(m);
    }
    
    @After
    public void tearDown() {
        instance.deleteConversation(-2);
    }

    /**
     * Test of createMsg method being valid, of class MessageDao.
     */
    @Test
    public void testCreateMsgValid() {
        boolean expResult = true;
        boolean result = instance.getConversation(-1).size() > 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of createMsg method being invalid, of class MessageDao.
     */
    @Test
    public void testCreateMsgInvalid() {
        boolean expResult = false;
        boolean result = instance.getConversation(-1).size() == 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteConversation method being valid, of class MessageDao.
     */
    @Test
    public void testDeleteConversationValid() {
        int result = instance.deleteConversation(-2);
        assertEquals(0, result);
    }
    
    /**
     * Test of deleteConversation method being invalid, of class MessageDao.
     */
    @Test
    public void testDeleteConversationInvalid() {
        int result = instance.deleteConversation(1);
        assertEquals(-5, result);
    }

    /**
     * Test of getConversation method being valid, of class MessageDao.
     */
    @Test
    public void testGetConversationValid() {
        boolean result = instance.getConversation(-1).size() > 0;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getConversation method being invalid, of class MessageDao.
     */
    @Test
    public void testGetConversationInvalid() {
        boolean result = instance.getConversation(-1).size() == 0;
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of markAsRead method being valid, of class MessageDao.
     */
    @Test
    public void testMarkAsReadValid() {
        boolean expResult = true;
        Message m1 = instance.getConversation(-1).get(0);
        instance.markAsRead(m1.getMsgId());
        boolean result = instance.getConversation(-2).get(0).getStatus();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of markAsRead method being invalid, of class MessageDao.
     */
    @Test
    public void testMarkAsReadInvalid() {
        boolean expResult = false;
        Message m1 = instance.getConversation(-1).get(0);
        instance.markAsRead(m1.getMsgId() - 1);
        boolean result = instance.getConversation(-2).get(0).getStatus();
        assertEquals(expResult, result);
    }  
}
