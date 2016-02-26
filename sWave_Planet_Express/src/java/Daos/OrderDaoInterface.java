/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
