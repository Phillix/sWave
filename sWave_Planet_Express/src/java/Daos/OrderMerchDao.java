/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

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
public class OrderMerchDao extends Dao implements OrderMerchDaoInterface {
    
    private final boolean DEBUG = sWave.Debugging.debug;

    private final String TABLE_NAME  = "ORDERMERCH";
    private final String ORDERID     = "ORDERID";
    private final String MERCHID     = "MERCHID";
    private final String QTY         = "QTY";
    private final String PRICE       = "PRICEPAID";
    
    /**
     * 
     * @param om OrderMerch object to create
     * @return int value indicating success
     */
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
    
    /**
     * 
     * @param orderId id of the order
     * @return Collection of OrderMerch belonging to passed in Order id
     */
    public ArrayList<OrderMerch> getOrderMerchInOrder(int orderId) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderMerch om = null;
        ArrayList<OrderMerch> orderMerch;

        try {

            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + ORDERID + " = ?");
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            orderMerch = new ArrayList<>();

            while(rs.next()) {
                om = new OrderMerch();

                om.setOrderId(rs.getInt(ORDERID));
                om.setMerchId(rs.getInt(MERCHID));
                om.setQty(rs.getInt(QTY));
                om.setPricePaid(rs.getDouble(PRICE));

                orderMerch.add(om);
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
        return orderMerch;
    }
}
