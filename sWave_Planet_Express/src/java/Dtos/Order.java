package Dtos;

import java.util.Objects;

/**
 *
 * @author Phillix
 */
public class Order {

    private int    orderId;
    private int    userId;
    private String dateOrdered;
    private double total;

    public Order() {
        orderId     = 0;
        userId      = 0;
        dateOrdered = "enter date";
        total       = 0;
    }

    /**
     * all fields except userId and total are auto generated
     * @param userId id of the user the order belongs to
     * @param total total cost of the order
     */
    public Order(int userId, double total) {
        orderId     = 0;
        this.userId = userId;
        this.total = total;
    }

    /**
     * Getting the id of the order
     * @return the id of the order
     */
    public int getOrderId() {
        return orderId;
    }
    
    /**
     * Setting the id of the order
     * @param orderId the new id of the order
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Getting the user id from the order
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * Setting the user id for the order
     * @param userId the new user id for the order
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getting the date the order was placed
     * @return the date the order was placed
     */
    public String getDateOrdered() {
        return dateOrdered;
    }

    /**
     * Setting the date that the order was placed
     * @param dateOrdered the new date that the order is placed
     */
    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    /**
     * Getting the total cost of the order
     * @return the total cost of the order
     */
    public double getTotal() {
        return total;
    }

    /**
     * Setting the total cost of the order
     * @param total the new total cost of the order
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * The toString method for providing an easy way of printing all details of the Order
     * @return a String representing all details of the order
     */
    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", userId=" + userId + ", dateOrdered=" + dateOrdered + ", total=" + total + '}';
    }

    /**
     * A hash code method for hashing each Order
     * @return an int with the hash of this Order
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.orderId;
        hash = 71 * hash + this.userId;
        hash = 71 * hash + Objects.hashCode(this.dateOrdered);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        return hash;
    }

    /**
     * An equals method for checking if this Order is equal to another object
     * @param obj The object you wish to compare this Order to
     * @return a boolean value representing if this Order is equal to the argument
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Order other = (Order)obj;
        if (this.orderId != other.orderId)
            return false;
        return this.userId == other.userId;
    }
}
