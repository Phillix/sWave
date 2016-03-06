package Daos;

import Dtos.Lock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Brian Millar
 */
public class LockDao extends Dao implements LockDaoInterface {
    private final boolean DEBUG = Debugging.Debug.debug;

    private final String TABLE_NAME = "LOCKS";
    private final String USERID     = "USERID";
    private final String SONGID     = "SONGID";

    @Override
    public Lock addLock(Lock x) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con = getConnection();
            ps  = con.prepareStatement("INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?)");
            ps.setInt(1, x.getUserid());
            ps.setInt(2, x.getSongid());
            ps.setLong(3, x.getLockTime());
            ps.executeUpdate();
        }
        catch(ClassNotFoundException | SQLException e) {
            if(DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(Exception e) {
                if(DEBUG)
                    e.printStackTrace();
            }
        }
        return x;
    }

    @Override
    public void releaseUserLocks(int userId) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con = getConnection();
            ps  = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " + USERID + " = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
        catch(Exception e) {
            if(DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(Exception e) {
                if(DEBUG)
                    e.printStackTrace();
            }
        }
    }

    @Override
    public void releaseSongLocks(int songId) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con = getConnection();
            ps  = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " + SONGID + " = ?");
            ps.setInt(1, songId);
            ps.executeUpdate();
        }
        catch(ClassNotFoundException | SQLException e) {
            if(DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(Exception e) {
                if(DEBUG)
                    e.printStackTrace();
            }
        }
    }

    @Override
    public void releaseAllLocks() {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con = getConnection();
            ps  = con.prepareStatement("DELETE FROM " + TABLE_NAME);
            rs  = ps.executeQuery();
        }
        catch(ClassNotFoundException | SQLException e) {
            if(DEBUG)
                e.printStackTrace();
        }
        finally {
            try {
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(Exception e) {
                if(DEBUG)
                    e.printStackTrace();
            }
        }
    }
}
