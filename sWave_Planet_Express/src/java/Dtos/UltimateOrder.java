/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public class UltimateOrder {
    
    private final int SIZE;
    private double total;
    private String dateOrdered;
    private int[] qty;
    private double[] price;
    private String[] title;
    
    public UltimateOrder() {
        SIZE = 0;  
    }
    public UltimateOrder(Order o, ArrayList<OrderMerch> om, ArrayList<Merch> m) {
        
        SIZE = m.size();
        qty = new int[SIZE];
        price = new double[SIZE];
        title = new String[SIZE];
        total = o.getTotal();
        dateOrdered = o.getDateOrdered();
        
        for(int i = 0; i < SIZE; i++) {
            title[i] = m.get(i).getTitle();
            qty[i] = om.get(i).getQty();
            price[i] = om.get(i).getPricePaid();
        }
        
    }

    public int getSize() {
        return SIZE;
    }
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public int getQty(int i) {
        return qty[i];
    }

    public void setQty(int[] qty) {
        this.qty = qty;
    }

    public double getPrice(int i) {
        return price[i];
    }

    public void setPrice(double[] price) {
        this.price = price;
    }

    public String getTitle(int i) {
        return title[i];
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "UltimateOrder{" + "SIZE=" + SIZE + ", total=" + total + ", dateOrdered=" + dateOrdered + ", qty=" + qty + ", price=" + price + ", title=" + title + '}';
    }    
    
}
