package Daos;

import Dtos.OrderSong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 * The OrderSongDao class is used for communicating with OrderSong table in the database
 * @author Phillix
 */
public class OrderSongDao extends Dao implements OrderSongDaoInterface {

    private final boolean DEBUG = sWave.Server.DEBUGGING;

    private final String TABLE_NAME = "ORDERSONG";
    private final String ORDERID    = "ORDERID";
    private final String SONGID     = "SONGID";
    private final String PRICE      = "PRICEPAID";
    
    /**
     * Default Constructor for OrderSongDao
     */
    public OrderSongDao() {
        super();
    }
    
    /**
     * Parameterized Constructor for OrderSongDao
     * @param ds The DataSource to use for connections
     */
    public OrderSongDao(DataSource ds) {
        super(ds);
    }
    
    /**
     * Used for creating a new ordersong to be added to the database
     * @param os The ordersong you wish to add
     * @return SUCCESS if it inserted correctly, OTHER otherwise
     */
    @Override
    public int createOrderSong(OrderSong os) {

        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, os.getOrderId());
            ps.setInt(2, os.getSongId());
            ps.setDouble(3, os.getPricePaid());

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
     * This song gets a list of the ordersongs in the ordersongs table
     * @param orderId The id of the order you want to get the ordersongs from
     * @return A list of all of the ordersongs
     */
    @Override
    public ArrayList<OrderSong> getOrderSongInOrder(int orderId) {
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderSong os = null;
        ArrayList<OrderSong> orderSong;

        try {

            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + ORDERID + " = ?");
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            orderSong = new ArrayList<>();

            while(rs.next()) {
                os = new OrderSong();

                os.setOrderId(rs.getInt(ORDERID));
                os.setSongId(rs.getInt(SONGID));
                os.setPricePaid(rs.getDouble(PRICE));

                orderSong.add(os);
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
        return orderSong;
    }

    /**
     * This method is used for deleting OrderSong based on an order id
     * @param orderId the id of the order to be deleted
     * @return an int indicating success, failure or exceptions
     */
    @Override
    public int deleteOrderSong(int orderId) {
        Connection con = null;
        PreparedStatement ps = null;

        try {

            con = getConnection();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ORDERID + " = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, orderId);

            int result = ps.executeUpdate();
            if (result > 0) return SUCCESS;
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
        return OTHER;
    }
}
