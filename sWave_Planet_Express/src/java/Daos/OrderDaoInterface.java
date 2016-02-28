package Daos;

import Dtos.Order;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface OrderDaoInterface {
    public int createOrder(Order o);
    public ArrayList<Order> getUserOrders(int userId);
}
