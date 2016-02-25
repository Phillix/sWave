/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

/**
 *
 * @author Phillix
 */
public class Order {
    
    private int orderId;
    private int userId;
    private String dateOrdered;
    
    public Order() {
        
        orderId = 0;
        userId = 0;
        dateOrdered = "enter date";
    }
    
    /**
     * 
     * @param userId id of user order belongs to
     */
    public Order(int userId) {
     
        orderId = 0;
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", userId=" + userId + ", dateOrdered=" + dateOrdered + '}';
    }
           
}
