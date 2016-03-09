package Daos;

import Dtos.OrderMerch;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface OrderMerchDaoInterface {
    public int createOrder(OrderMerch om);
    public ArrayList<OrderMerch> getOrderMerchInOrder(int orderId);
}
