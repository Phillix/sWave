package Daos;

import Dtos.Friend;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

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
     * Default Constructor for FriendDao
     */
    public FriendDao() {
        super();
    }
    
    /**
     * Parameterized Constructor for FriendDao
     * @param ds The DataSource to use for connections
     */
    public FriendDao(DataSource ds) {
        super(ds);
    }
    
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
            ps.setString(4, String.valueOf(f.getStatus()));

            ps.executeUpdate();
            return SUCCESS;
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
                                       " WHERE (" + ID + " = ? OR " + FRIENDID + " = ?) AND " + STATUS + " = 'c'" +
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
    
    /**
     * 
     * @param userId the id of the user you wish to return pending requests belonging to
     * @return a Collection of friends whose status is 'p' (pending)
     */
    public ArrayList<Friend> getPendingFriendRequests(int userId) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        Friend f              = null;
        ArrayList<Friend> friends;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT * FROM " + TABLE_NAME +
                                       " WHERE (" + ID + " = ? OR " + FRIENDID + " = ?) AND " + STATUS + " = 'p'" +
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
    
    public int removeFriend(int userId,int friendId) {
        Connection con       = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE (" + ID + " = ? OR " + FRIENDID + " = ? )" +
                                      "AND (" + FRIENDID + " = ? OR " + ID + " = ?)");
           
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ps.setInt(3, friendId);
            ps.setInt(4, friendId);
            if(ps.executeUpdate() > 0) return SUCCESS;
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
    
    public int confirmFriend(int userId, int friendId) {
        Connection con       = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE " + TABLE_NAME + " SET " + STATUS + " = 'c' " +
                                      "WHERE (" + ID + " = ? OR " + FRIENDID + " = ?) " +
                                      "AND (" + FRIENDID + " = ? OR " + ID + " = ?)");
            
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ps.setInt(3, friendId);
            ps.setInt(4, friendId);
            if(ps.executeUpdate() > 0) return SUCCESS;
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
