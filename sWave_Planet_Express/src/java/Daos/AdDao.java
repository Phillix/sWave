package Daos;

import Dtos.Ad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * The AdDao class is used for communicating with Ad table in the database
 * @author Phillix
 */
public class AdDao extends Dao implements AdDaoInterface {
    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME = "ADS";
    private final String ID         = "ADID";
    private final String URL        = "ADURL";

    public AdDao() {
        super();
    }
    
    public AdDao(DataSource ds) {
        super(ds);
    }
    
    /**
     * Used for getting an ad based on an ad id
     * @param id random generated ad id
     * @return ad to display
     */
    @Override
    public Ad getAd(int id) {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        Ad ad                = null;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()) {
                ad = new Ad();
                ad.setAdId(id);
                ad.setAdUrl(rs.getString(URL));
            }
        }
        catch(SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return null;
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
            catch(SQLException e) {
                if(DEBUG)
                    e.printStackTrace();
               return null;
            }
        }
        return ad;
    }

    /**
     * Gets the max id in the database
     * @return max id for use in creating random id for getAd method
     */
    @Override
    public int getMaxAdId() {
        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT MAX(" + ID + ") FROM " + TABLE_NAME);
            rs  = ps.executeQuery();

            if(rs.next()) {
                int maxId = rs.getInt("MAX(" + ID + ")");
                return maxId;
            }
        }
        catch(SQLException e) {
            if(DEBUG)
                e.printStackTrace();
            return SQLEX;
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
               return CONNCLOSEFAIL;
            }
        }
        return OTHER;
    }
}
