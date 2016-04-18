package Daos;

import Dtos.Lock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Brian Millar
 */
public class LockDao extends Dao implements LockDaoInterface {
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME = "LOCKS";
    private final String USERID     = "USERID";
    private final String SONGID     = "SONGID";

    /**
     * Default Constructor for LockDao
     */
    public LockDao() {
        super();
    }
    
    /**
     * Parameterized Constructor for LockDao
     * @param ds The DataSource to use for connections
     */
    public LockDao(DataSource ds) {
        super(ds);
    }
    
    /**
     * Used for adding a lock
     * @param x The lock you wish to add
     * @return The lock you added
     */
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
        catch(SQLException e) {
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

    /**
     * Used for releasing a particular users locks
     * @param userId The id of the user you want to break free
     */
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

    /**
     * Releasing all locks on a particular song
     * @param songId The id of the song you wish to drop locks for
     */
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
        catch(SQLException e) {
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

    /**
     * Used for releasing all locks on everything
     */
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
        catch(SQLException e) {
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
    /**
     * 
     * @param songid
     * @return number of locks on a particular file
     */
    @Override
    public int getNumLocks(int songid) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        int count = 0;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + SONGID + " = ?");
            ps.setInt(1, songid);
            rs  = ps.executeQuery();

            while(rs.next()) {
                count++;
            }
        }
        catch(Exception e) {
            if(DEBUG)
                e.printStackTrace();
            return 0;
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
                return 0;
            }
        }
        return count;
    }
}