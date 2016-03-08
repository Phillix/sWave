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
public class OrderSong {
    
    private int orderId;
    private int songId;
    private double pricePaid;
    
    public OrderSong() {
        orderId = -1;
        songId = -1;
        pricePaid = 9.00;
    }

    public OrderSong(int orderId, int songId, double pricePaid) {
        this.orderId = orderId;
        this.songId = songId;
        this.pricePaid = pricePaid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    @Override
    public String toString() {
        return "OrderSong{" + "orderId=" + orderId + ", songId=" + songId + ", pricePaid=" + pricePaid + '}';
    }
}
