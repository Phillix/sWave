/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

import java.util.Objects;

/**
 *
 * @author Phillix
 */
public class Order {
    
    private int orderId;
    private int userId;
    private String dateOrdered;
    private double total;
    
    public Order() {
        
        orderId = 0;
        userId = 0;
        dateOrdered = "enter date";
        total = 0;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", userId=" + userId + ", dateOrdered=" + dateOrdered + ", total=" + total + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.orderId;
        hash = 71 * hash + this.userId;
        hash = 71 * hash + Objects.hashCode(this.dateOrdered);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    
    
       
}
