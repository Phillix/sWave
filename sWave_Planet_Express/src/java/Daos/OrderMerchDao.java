/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.OrderMerch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Phillix
 */
public class OrderMerchDao extends Dao {
    
    private final boolean DEBUG = Debugging.Debug.debug;

    private final String TABLE_NAME  = "ORDERMERCH";
    private final String ORDERID     = "ORDERID";
    private final String MERCHID     = "MERCHID";
    private final String QTY         = "QTY";
    private final String PRICE       = "PRICEPAID";
    
    public int createOrder(OrderMerch om) {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, om.getOrderId());
            ps.setInt(2, om.getMerchId());
            ps.setInt(3, om.getQty());
            ps.setDouble(4, om.getPricePaid());

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
    
    
}
