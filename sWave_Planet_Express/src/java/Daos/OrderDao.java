/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Order;
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
public class OrderDao extends Dao implements OrderDaoInterface {
    
    private final boolean DEBUG = false;
    
    private final String TABLE_NAME  = "ORDERS";
    private final String ID          = "ORDERID";
    private final String USERID      = "USERID";
    private final String DATEORDERED = "DATEORDERED";
    private final String TOTAL       = "TOTAL";
    
    public int createOrder(Order o) {
        
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
           
            con = getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?)";
           
            java.util.Date iStillHateDates = new java.util.Date();
            Date orderDate = new Date(iStillHateDates.getTime());
            
            ps = con.prepareStatement(query);
            
            ps.setInt(1, o.getOrderId());
            ps.setInt(2, o.getUserId());
            ps.setDate(3, orderDate);
            ps.setDouble(4, o.getTotal());
            
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
    
    public ArrayList<Order> getUserOrders(int userId) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order o = null;
        ArrayList<Order> orders;
        
        try {
            
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + USERID + " = ? ORDER BY " + DATEORDERED);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            orders = new ArrayList<>();
            
            while(rs.next()) {
                
                o = new Order();
                
                o.setOrderId(rs.getInt(ID));
                o.setUserId(rs.getInt(USERID));
                o.setDateOrdered(rs.getDate(DATEORDERED).toString());
                o.setTotal(rs.getDouble(TOTAL));
               
                orders.add(o);
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
        return orders;
    }
}
