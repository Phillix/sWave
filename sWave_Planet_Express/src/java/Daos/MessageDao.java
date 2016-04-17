/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Message;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public class MessageDao extends Dao implements MessageDaoInterface {
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME  = "MESSAGE";
    private final String ID          = "MSGID";
    private final String SENDER      = "SENDER";
    private final String RECEIVER    = "RECEIVER";
    private final String DATE        = "MSGDATE";
    private final String CONTENT     = "CONTENT";
    private final String STATUS      = "STATUS"; // 0 for unread 1 for read
    
    /**
     * 
     * @param m the message to write to database
     * @return int value indicating success/failures
     */
    public int createMsg(Message m) {

        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "INSERT INTO " +
                           TABLE_NAME + " (" +
                           SENDER     + ", " +
                           RECEIVER   + ", " +
                           DATE       + ", " +
                           CONTENT    + ", " +
                           STATUS     + ") VALUES (?,?,?,?,?)";
                           

            java.util.Date utilDate = new java.util.Date();
            Date msgDate = new Date(utilDate.getTime());

            ps = con.prepareStatement(query);

            ps.setInt(1, m.getSender());
            ps.setInt(2, m.getReceiver());
            ps.setDate(3, msgDate);
            ps.setString(4, m.getContent());
            ps.setBoolean(5, false);

            ps.executeUpdate();
            return SUCCESS;
        }
        catch (ClassNotFoundException e) {
            if(DEBUG)
                e.printStackTrace();
            return CLASSNOTFOUND;
        }
        catch (SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return SQLEX;
        }
        finally {
            try {
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
                return CONNCLOSEFAIL;
            }
        }
    }
    
    /**
     * 
     * @param friendId retrieve conversation from this users id
     * @return Collection of Message objects
     */
    public ArrayList<Message> getConversation(int friendId) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        Message m              = null;
        ArrayList<Message> conversation;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT * FROM " + TABLE_NAME +
                                       " WHERE " + SENDER + " = ? OR " + RECEIVER + " = ? " +
                                       " ORDER BY " + DATE);
            ps.setInt(1, friendId);
            ps.setInt(2, friendId);
            rs     = ps.executeQuery();
            conversation = new ArrayList<>();

            while(rs.next()) {
                m = new Message();

                m.setMsgId(rs.getInt(ID));
                m.setSender(rs.getInt(SENDER));
                m.setReceiver(rs.getInt(RECEIVER));
                m.setDate(rs.getDate(DATE).toString());
                m.setContent(rs.getString(CONTENT));
                m.setStatus(rs.getBoolean(STATUS));

                conversation.add(m);
            }
        }
        catch(Exception e) {
            if(DEBUG)
                e.printStackTrace();
            return null;
        }
        finally {
            try {
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
                if (rs != null)
                    rs.close();
            }
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
                return null;
            }
        }
        return conversation;
    }
    
    public int markAsRead(int msgId) {
        Connection con       = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + TABLE_NAME + " SET " + STATUS + " = ? " +
                                      "WHERE " + ID + " = ?");
            
            ps.setBoolean(1, true);
            ps.setInt(2, msgId);
            if(ps.executeUpdate() > 0) return SUCCESS;
        }
        catch (ClassNotFoundException ex1) {
            if (DEBUG)
                ex1.printStackTrace();
            return CLASSNOTFOUND;
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {

            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return SQLEX;
            }
        }
        return OTHER;
    }
    
    public int deleteConversation(int friendId) {
        Connection con       = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " + SENDER + " = ? OR " + RECEIVER + " = ?");
           
            ps.setInt(1, friendId);
            ps.setInt(2, friendId);
            if(ps.executeUpdate() > 0) return SUCCESS;
        }
        catch (ClassNotFoundException ex1) {
            if (DEBUG)
                ex1.printStackTrace();
            return CLASSNOTFOUND;
        }
        catch (SQLException ex2) {
            if (DEBUG)
                ex2.printStackTrace();
            return SQLEX;
        }
        finally {

            try {
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
                return SQLEX;
            }
        }
        return OTHER;
    }   
}