package Daos;

import Dtos.OrderSong;
import java.util.ArrayList;

/**
 *
 * @author Austin
 */
public interface OrderSongDaoInterface {
    public int createOrderSong(OrderSong os);
    public ArrayList<OrderSong> getOrderSongInOrder(int orderId);
}
