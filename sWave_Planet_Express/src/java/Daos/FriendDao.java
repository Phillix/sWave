/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Friend;
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
public class FriendDao extends Dao implements FriendDaoInterface {
    
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME  = "FRIEND";
    private final String ID          = "USERID";
    private final String FRIENDID    = "FRIENDID";
    private final String DATE        = "FRIENDSHIPDATE";
    private final String STATUS      = "STATUS";
    
    /**
     * 
     * @param f the friend you wish to befriend
     * @return int value indicating success/failures
     */
    public int requestFriend(Friend f) {

        Connection con       = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?)";

            java.util.Date utilDate = new java.util.Date();
            Date friendshipDate = new Date(utilDate.getTime());

            ps = con.prepareStatement(query);

            ps.setInt(1, f.getUserId());
            ps.setInt(2, f.getFriendId());
            ps.setDate(3, friendshipDate);
            ps.setDouble(4, 'p');

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
     * @param userId the user the friendlist belongs to
     * @return ArrayList of friend objects
     */
    public ArrayList<Friend> getUserFriends(int userId) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        Friend f              = null;
        ArrayList<Friend> friends;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT * FROM " + TABLE_NAME +
                                       " WHERE " + ID + " = ? OR " + FRIENDID + " = ? AND " + STATUS + " = 'c'" +
                                       " ORDER BY " + DATE);
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            rs     = ps.executeQuery();
            friends = new ArrayList<>();

            while(rs.next()) {
                f = new Friend();

                f.setUserId(rs.getInt(ID));
                f.setFriendId(rs.getInt(FRIENDID));
                f.setFriendshipDate(rs.getDate(DATE).toString());
                f.setStatus(rs.getString(STATUS).charAt(0));

                friends.add(f);
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
        return friends;
    }
    
    
}
