package Dtos;

/**
 * This class is used for creating and managing OrderMerch objects
 * @author Phillix
 */
public class OrderMerch {

    private int orderId;
    private int merchId;
    private int qty;
    private double pricePaid;
    private byte[] custimg;

    /**
     * Default constructor for OrderMerch
     */
    public OrderMerch() {
        orderId   = -1;
        merchId   = -1;
        qty       = 1;
        pricePaid = 9.99;
        custimg   = null;
    }

    /**
     * Overloaded constructor for OrderMerch
     * @param orderId the id of the Order
     * @param merchId the id of the merchandise
     * @param qty the quantity of merch being bought
     * @param pricePaid the amount paid for the merch
     * @param custimg the custom image to go on particular items
     */
    public OrderMerch(int orderId, int merchId, int qty, double pricePaid, byte[] custimg) {
        this.orderId = orderId;
        this.merchId = merchId;
        this.qty = qty;
        this.pricePaid = pricePaid;
        this.custimg = custimg;
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
     * Getting the id of the merchandise
     * @return the id of the merchandise
     */
    public int getMerchId() {
        return merchId;
    }

    /**
     * Setting the id of the merch in the order
     * @param merchId the new id of the merch to go in the order
     */
    public void setMerchId(int merchId) {
        this.merchId = merchId;
    }

    /**
     * Getting the quantity of merch in the order
     * @return the quantity of merch in the order
     */
    public int getQty() {
        return qty;
    }

    /**
     * Setting the quantity of merch in the order
     * @param qty the new quantity of merch in the order
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * Getting the price paid for the merch
     * @return the price paid for the merch
     */
    public double getPricePaid() {
        return pricePaid;
    }

    /**
     * Setting the price paid for the merchandise
     * @param pricePaid the new price paid for the merchandise
     */
    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    /**
     * Getting the custom image on the merch
     * @return the custom image on the merch
     */
    public byte[] getCustImg() {
        return custimg;
    }

    /**
     * Setting the custom image on the merch
     * @param custimg the new custom image on the merch
     */
    public void setCustImg(byte[] custimg) {
        this.custimg = custimg;
    }

    /**
     * The toString method for easier printing of the contents of this class
     * @return a String with all of the details of the OrderMerch
     */
    @Override
    public String toString() {
        return "OrderMerch{" + "orderId=" + orderId + ", merchId=" + merchId + ", qty=" + qty + ", pricePaid=" + pricePaid + '}';
    }

}
