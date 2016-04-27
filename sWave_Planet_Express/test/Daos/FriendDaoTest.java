package Daos;

import Dtos.Friend;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class for testing FriendDao methods, for valid and invalid
 * @author Phillix
 * @author Austin
 */
public class FriendDaoTest {
    static MyDataSource ds = new MyDataSource();
    Friend f1 = new Friend(-1, -2);
    Friend f2 = new Friend(1, -2);
    static FriendDao instance;
    
    @BeforeClass
    public static void setUpClass() {
        instance = new FriendDao(ds);
    }
    
    @Before
    public void setUp() {
        instance.requestFriend(f1);
        instance.requestFriend(f2);
    }
    
    @After
    public void tearDown() {
        instance.removeFriend(-1, -2);
        instance.removeFriend(1, -2);
    }
    
    /**
     * Test of confirmFriend method being valid, of class FriendDao.
     */
    @Test
    public void testConfirmFriendValid() {
        instance.requestFriend(f1);
        instance.confirmFriend(-1, -2);
        boolean expResult = true;
        boolean result = instance.getUserFriends(-1).stream().anyMatch(f -> f.getFriendId() == -2);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of confirmFriend method being invalid, of class FriendDao.
     */
    @Test
    public void testConfirmFriendInvalid() {
        instance.requestFriend(f1);
        instance.confirmFriend(-1, -2);
        boolean expResult = false;
        boolean result = instance.getUserFriends(-1).stream().anyMatch(f -> f.getFriendId() == -3);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPendingFriendRequests method being valid, of class FriendDao.
     */
    @Test
    public void testGetPendingFriendRequestsValid() {
        instance.requestFriend(f1);
        instance.requestFriend(f2);
        ArrayList<Friend> friends = instance.getPendingFriendRequests(-2);
        boolean result = friends.size() == 2;
        boolean expResult = true;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPendingFriendRequests method being invalid, of class FriendDao.
     */
    @Test
    public void testGetPendingFriendRequestsInvalid() {
        ArrayList<Friend> friends = instance.getPendingFriendRequests(-2);
        boolean result = friends.size() == 3;
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUserFriends method being valid, of class FriendDao.
     */
    @Test
    public void testGetUserFriendsValid() {
        instance.confirmFriend(-1, -2);
        int result = instance.getUserFriends(-1).size();
        int expResult = 1;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUserFriends method being invalid, of class FriendDao.
     */
    @Test
    public void testGetUserFriendsInvalid() {
        instance.confirmFriend(-1, -2);
        boolean result = instance.getUserFriends(-1).size() == 2;
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removeFriend method being valid, of class FriendDao.
     */
    @Test
    public void testRemoveFriendValid() {
        instance.confirmFriend(-1, -2);
        instance.removeFriend(-1, -2);
        instance.confirmFriend(1, -2);
        //The user should have one friend as we added two and deleted one
        int result = instance.getUserFriends(-2).size();
        int expResult = 1;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of removeFriend method being invalid, of class FriendDao.
     */
    @Test
    public void testRemoveFriendInvalid() {
        instance.confirmFriend(-1, -2);
        instance.removeFriend(-1, -2);
        instance.confirmFriend(1, -2);
        //The user should have no friends as we deleted the friend
        boolean result = instance.getUserFriends(-1).size() > 0;
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of requestFriend method being valid, of class FriendDao.
     */
    @Test
    public void testRequestFriendValid() {
        int initial = instance.getPendingFriendRequests(-2).size();
        instance.requestFriend(new Friend(2, -2));
        int result = instance.getPendingFriendRequests(-2).size();
        assertEquals(initial + 1, result);
        instance.removeFriend(2, -2);
    }
    
    /**
     * Test of requestFriend method being invalid, of class FriendDao.
     */
    @Test
    public void testRequestFriendInvalid() {
        int initial = instance.getPendingFriendRequests(-2).size();
        instance.requestFriend(new Friend(2, -2));
        int result = instance.getPendingFriendRequests(-2).size();
        assertNotEquals(initial, result);
        instance.removeFriend(2, -2);
    }
}
