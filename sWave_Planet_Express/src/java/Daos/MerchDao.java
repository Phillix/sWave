package Daos;

import Dtos.Merch;
import Dtos.OrderMerch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public class MerchDao extends Dao implements MerchDaoInterface {
    
    private final boolean DEBUG = Debugging.Debug.debug;

    private final String TABLE_NAME = "MERCH";
    private final String ID         = "MERCHID";
    private final String TITLE      = "TITLE";
    private final String PRICE      = "PRICE";
    
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
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, m.getMerchId());
            ps.setString(2, m.getTitle());
            ps.setDouble(3, m.getPrice());

            ps.executeUpdate();
            return SUCCESS;
        }
        catch (ClassNotFoundException e) {
            if(DEBUG) {
                e.printStackTrace();
            }
            return CLASSNOTFOUND;
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
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " IN(";
            
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
}
