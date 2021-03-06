package Daos;

import Dtos.Merch;
import Dtos.OrderMerch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * The MerchDao class is used for communicating with Merch table in the database
 * @author Austin
 * @author Phillix
 */
public class MerchDao extends Dao implements MerchDaoInterface {

    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME = "MERCH";
    private final String ID         = "MERCHID";
    private final String TITLE      = "TITLE";
    private final String PRICE      = "PRICE";
    private final String THREED     = "THREED";

    /**
     * Default Constructor for MerchDao
     */
    public MerchDao() {
        super();
    }
    
    /**
     * Parameterized Constructor for MerchDao
     * @param ds The DataSource to use for connections
     */
    public MerchDao(DataSource ds) {
        super(ds);
    }
    
    /**
     * Used for creating a new Merch
     * @param m Merch object to create
     * @return int value indicating success
     */
    @Override
    public int createMerch(Merch m) {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, m.getMerchId());
            ps.setString(2, m.getTitle());
            ps.setDouble(3, m.getPrice());
            ps.setString(4, m.getThreed());

            ps.executeUpdate();
            return SUCCESS;
        }
        catch (SQLException e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return SQLEX;
        }
        finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
            }
            catch(SQLException e) {
                if(DEBUG) {
                    e.printStackTrace();
                }
                return CONNCLOSEFAIL;
            }
        }
    }
    
    /**
     * This method is used for getting a Merch object based on its id
     * @param merchid
     * @return a Merch object with the given id
     */
    @Override
    public Merch getMerchById(int merchid) {
        Connection con        = null;
        PreparedStatement ps  = null;
        ResultSet rs          = null;

        try {
            con          = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = ?";
            ps           = con.prepareStatement(query);
            ps.setInt(1, merchid);
            rs = ps.executeQuery();

            if (rs.next()) {
                Merch m = new Merch(rs.getString(TITLE),
                                    rs.getDouble(PRICE),
                                    rs.getString(THREED));
                m.setMerchId(rs.getInt(ID));
                return m;
            }
        }
        catch (SQLException ex1) {
            if (DEBUG)
                ex1.printStackTrace();
        }
        finally {
            try {
                if(rs  != null)
                    rs.close();
                if(ps  != null)
                    ps.close();
                if(con != null)
                    freeConnection(con);
            }
            catch(SQLException e) {
                if (DEBUG)
                    e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * This method is used to get all of the merch from the database
     * @return Collection of all merchandise on offer in alphabetical order
     */
    @Override
    public ArrayList<Merch> viewMerchAlpha() {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Merch m = null;
        ArrayList<Merch> merch;

        try {

            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " ORDER BY " + TITLE);
           
            rs = ps.executeQuery();
            merch = new ArrayList<>();

            while(rs.next()) {
                m = new Merch();

                m.setMerchId(rs.getInt(ID));
                m.setTitle(rs.getString(TITLE));
                m.setPrice(rs.getDouble(PRICE));

                merch.add(m);
            }
        }
        catch(Exception e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return null;
        }
        finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch(SQLException e) {
                if(DEBUG) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return merch;
    }
    
    /**
     * Used for getting all of the merch in a particular order
     * @param ids the Collection of orderMerch you want to retrieve Merch for
     * @return Collection of Merch belonging to an Order
     */
    @Override
    public ArrayList<Merch> getMerchInOrder(ArrayList<OrderMerch> ids) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Merch m = null;
        ArrayList<Merch> merch;

        try {

            con = getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " IN (";
            
            //build a query String and remove final comma
            for(int i = 0; i < ids.size(); i++) {
                query += "?,";
            }
            query = (query.substring(0,query.length()-1)) + ")";
            
            //Set the ids into placeholders
            ps = con.prepareStatement(query);
            for(int i = 1; i < ids.size()+1; i++) {
                ps.setInt(i, ids.get(i-1).getMerchId());
            }
           
            rs = ps.executeQuery();
            merch = new ArrayList<>();

            while(rs.next()) {
                m = new Merch();

                m.setMerchId(rs.getInt(ID));
                m.setTitle(rs.getString(TITLE));
                m.setPrice(rs.getDouble(PRICE));

                merch.add(m);
            }
        }
        catch(Exception e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return null;
        }
        finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch(SQLException e) {
                if(DEBUG) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return merch;
    }

    /**
     * Used for searching for merch
     * @param searchWord String of text to use in searching
     * @return a Collection of successful Merch matches
     */
    @Override
    public ArrayList<Merch> searchMerch(String searchWord) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Merch m = null;
        ArrayList<Merch> merch;

        try {

            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + TITLE + " LIKE ?");
            ps.setString(1, "%" + searchWord + "%");
            rs = ps.executeQuery();
            merch = new ArrayList<>();

            while(rs.next()) {
                m = new Merch();

                m.setMerchId(rs.getInt(ID));
                m.setTitle(rs.getString(TITLE));
                m.setPrice(rs.getDouble(PRICE));

                merch.add(m);
            }
        }
        catch(Exception e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return null;
        }
        finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch(SQLException e) {
                if(DEBUG) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return merch;
    }
    
    /**
     * Method for removing merchandise from the database
     * @param id The id of the merchandise you want to remove
     * @return An integer value representing success or failure or exceptions
     */
    @Override
    public int removeMerch(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {

            con = getConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = ?");
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0) return SUCCESS;
        }
        catch(Exception e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return OTHER;
        }
        finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    freeConnection(con);
                }
            }
            catch(SQLException e) {
                if(DEBUG) {
                    e.printStackTrace();
                }
                return SQLEX;
            }
        }
        return OTHER;
    }
}
