package Daos;

import Dtos.Merch;
import Dtos.Order;
import Dtos.OrderMerch;
import Dtos.OrderSong;
import Dtos.UltimateOrder;
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

    private final boolean DEBUG = sWave.Debugging.debug;

    private final String TABLE_NAME  = "ORDERS";
    private final String ID          = "ORDERID";
    private final String USERID      = "USERID";
    private final String DATEORDERED = "DATEORDERED";
    private final String TOTAL       = "TOTAL";

    /**
     * 
     * @param o the order object to be created
     * @return int value indicating success/failures
     */
    public int createOrder(Order o) {

        Connection con       = null;
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
            if(DEBUG)
                e.printStackTrace();
            return CLASSNOTFOUND;
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

    public ArrayList<Order> getUserOrders(int userId) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        Order o              = null;
        ArrayList<Order> orders;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT * FROM " + TABLE_NAME +
                                       " WHERE "        + USERID     +
                                       " = ? ORDER BY " + DATEORDERED);
            ps.setInt(1, userId);
            rs     = ps.executeQuery();
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
        return orders;
    }
    
    /**
     * 
     * @param userId the id of the user whos order details are wanted
     * @return ArrayList of UltimateOrders
     */
    public ArrayList<UltimateOrder> getFullOrders(int userId) {

        UltimateOrder ultimateOrder;
        OrderMerchDao omDao = new OrderMerchDao();
        OrderSongDao osDao  = new OrderSongDao();
        MerchDao mDao = new MerchDao();
        
        //get collection of users orders
        ArrayList<Order> orders = getUserOrders(userId);
        ArrayList<UltimateOrder> ultiOrders = new ArrayList<UltimateOrder>();
        ArrayList<OrderMerch> oMerch;
        ArrayList<Merch> merch;
        ArrayList<OrderSong> oSong;

        //iterate through orders passing id into other function
        if(orders != null) {
            for(int i = 0; i < orders.size(); i++) {
                //build arrayList of orderMerch using order ids
                oMerch = omDao.getOrderMerchInOrder(orders.get(i).getOrderId());
                oSong  = osDao.getOrderSongInOrder(orders.get(i).getOrderId());
                
                if(oMerch != null && oSong == null) {
                    //get all individual merch beloning to an orderMerch
                    merch = mDao.getMerchInOrder(oMerch);
                    //create an ultimateOrder with an order and its relevent orderMerch and Merch Collections 
                    ultimateOrder = new UltimateOrder(orders.get(i),oMerch,merch);
                    //add to Collection of UltimateOrders
                    ultiOrders.add(ultimateOrder);
                } else if(oSong != null && oMerch == null) {
                    ultimateOrder = new UltimateOrder(orders.get(i),oSong);
                    ultiOrders.add(ultimateOrder);
                } else if(oSong != null && oMerch != null){
                    merch = mDao.getMerchInOrder(oMerch);
                    ultimateOrder = new UltimateOrder(orders.get(i),oMerch,merch,oSong);
                    ultiOrders.add(ultimateOrder);
                }
            }
        }

        return ultiOrders;
    }
    
    public Order getCurrentOrder(int userId) {

        Connection con       = null;
        PreparedStatement ps = null;
        ResultSet rs         = null;
        Order o              = null;

        try {
            con = getConnection();
            ps  = con.prepareStatement("SELECT * FROM " + TABLE_NAME +
                                       " WHERE "        + USERID     +
                                       " = ? ORDER BY " + DATEORDERED + " DESC LIMIT 1");
            ps.setInt(1, userId);
            rs     = ps.executeQuery();

            while(rs.next()) {
                o = new Order();

                o.setOrderId(rs.getInt(ID));
                o.setUserId(rs.getInt(USERID));
                o.setDateOrdered(rs.getDate(DATEORDERED).toString());
                o.setTotal(rs.getDouble(TOTAL));;
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
        return o;
    }
}
