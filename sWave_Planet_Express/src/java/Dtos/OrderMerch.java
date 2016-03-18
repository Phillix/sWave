package Dtos;

/**
 *
 * @author Phillix
 */
public class OrderMerch {

    private int orderId;
    private int merchId;
    private int qty;
    private double pricePaid;
    private byte[] custimg;

    public OrderMerch() {
        orderId   = -1;
        merchId   = -1;
        qty       = -1;
        pricePaid = -9.99;
        custimg   = null;
    }

    public OrderMerch(int orderId, int merchId, int qty, double pricePaid, byte[] custimg) {
        this.orderId = orderId;
        this.merchId = merchId;
        this.qty = qty;
        this.pricePaid = pricePaid;
        this.custimg = custimg;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMerchId() {
        return merchId;
    }

    public void setMerchId(int merchId) {
        this.merchId = merchId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public byte[] getCustImg() {
        return custimg;
    }

    public void setCustImg(byte[] custimg) {
        this.custimg = custimg;
    }

    @Override
    public String toString() {
        return "OrderMerch{" + "orderId=" + orderId + ", merchId=" + merchId + ", qty=" + qty + ", pricePaid=" + pricePaid + '}';
    }

}
