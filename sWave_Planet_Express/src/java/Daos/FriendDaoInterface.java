/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Friend;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface FriendDaoInterface {
    
    public int requestFriend(Friend f);
    public ArrayList<Friend> getUserFriends(int userId);
    public ArrayList<Friend> getPendingFriendRequests(int userId);
    public int removeFriend(int userId, int friendId);
    public int confirmFriend(int userId, int friendId);
}
