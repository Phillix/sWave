package Dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Phillix
 */
public class Merch implements Serializable {

    private int    merchId;
    private String title;
    private double price;

    public Merch() {
        merchId = 0;
        title   = "default";
        price   = 9.99;
    }

    /**
     *
     * @param title title of product item
     * @param price price of item
     */
    public Merch(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public int getMerchId() {
        return merchId;
    }

    public void setMerchId(int merchId) {
        this.merchId = merchId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Merch{" + "merchId=" + merchId + ", title=" + title + ", price=" + price + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Merch other = (Merch) obj;
        return this.merchId == other.merchId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }
}
