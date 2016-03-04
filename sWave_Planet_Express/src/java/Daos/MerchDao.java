/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Merch;
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

}
