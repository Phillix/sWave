package Dtos;

/**
 *
 * @author Phillix
 */
public class Merch {
    
    private int merchId;
    private String title;
    private double price;
    
    public Merch() {
        merchId = 0;
        title = "default";
        price = 9.99;
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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Merch other = (Merch) obj;
        if (this.merchId != other.merchId) {
            return false;
        }
        return true;
    }
    
    
}
