package Daos;

import Dtos.Order;
import Dtos.UltimateOrder;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface OrderDaoInterface {
    public int createOrder(Order o);
    public ArrayList<Order> getUserOrders(int userId);
    public ArrayList<UltimateOrder> getFullOrders(int userId);
    public Order getCurrentOrder(int userId);
}
