package Dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is used for creating and handling merchandise
 * @author Phillix
 */
public class Merch implements Serializable {

    private int    merchId;
    private String title;
    private double price;
    private String threed;

    /**
     * Default constructor for Merch
     */
    public Merch() {
        merchId = 0;
        title   = "default";
        price   = 9.99;
        threed = null;
    }

    /**
     * Constructor for Merch
     * @param title title of product item
     * @param price price of item
     */
    public Merch(String title, double price, String threed) {
        this.title = title;
        this.price = price;
        this.threed = threed;
    }
    
    /**
     * Getting the threed URL
     * @return The threed String
     */
    public String getThreed() {
        return threed;
    }
    
    /**
     * Setting the threed URL
     * @param threed The threed String you wish to change it to
     */
    public void setThreed(String threed) {
        this.threed = threed;
    }

    /**
     * Getting the MerchId
     * @return the id of the merchandise
     */
    public int getMerchId() {
        return merchId;
    }

    /**
     * Setting the MerchId
     * @param merchId The id of the merchandise you're changing it to
     */
    public void setMerchId(int merchId) {
        this.merchId = merchId;
    }

    /**
     * Getting the title of the merchandise
     * @return A String containing the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setting the title of the merchandise
     * @param title The title you wish to change the merchandise to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getting the price of the merchandise
     * @return The price of the merchandise
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setting the price of the merchandise
     * @param price The price you wish to change the merchandise to
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * ToString method for clean printing
     * @return A toString containing the details of the merchandise
     */
    @Override
    public String toString() {
        return "Merch{" + "merchId=" + merchId + ", title=" + title + ", price=" + price + '}';
    }

    /**
     * Equals method for comparing merchandise, based on id
     * @param obj The object you wish to compare this object to
     * @return A boolean indicating if the objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Merch other = (Merch) obj;
        return this.merchId == other.merchId;
    }

    /**
     * HashCode method for hashing merchandise
     * @return An hashed int for merchandise
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.title);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }
}
