/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Message;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface MessageDaoInterface {
    
    public int createMsg(Message m);
    public ArrayList<Message> getConversation(int friendId);
    public int markAsRead(int msgId);
    public int deleteConversation(int friendId);
}
