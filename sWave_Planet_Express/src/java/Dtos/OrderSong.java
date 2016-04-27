package Dtos;

/**
 * The class is used for creating and managing OrderSong objects
 * @author Phillix
 */
public class OrderSong {
    
    private int orderId;
    private int songId;
    private double pricePaid;
    
    /**
     * Default constructor for creating OrderSongs
     */
    public OrderSong() {
        orderId = -1;
        songId = -1;
        pricePaid = 9.00;
    }
    
    /**
     * Overloaded constructor for creating OrderSongs
     * @param orderId the id of the order
     * @param songId the id of the song
     * @param pricePaid the price paid for the song
     */
    public OrderSong(int orderId, int songId, double pricePaid) {
        this.orderId = orderId;
        this.songId = songId;
        this.pricePaid = pricePaid;
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
     * Getting the id of the song in the order
     * @return the id of the song
     */
    public int getSongId() {
        return songId;
    }

    /**
     * Setting the id of the song in the order
     * @param songId the new id of the song
     */
    public void setSongId(int songId) {
        this.songId = songId;
    }

    /**
     * Getting the price paid for the songs
     * @return the price paid for the songs
     */
    public double getPricePaid() {
        return pricePaid;
    }

    /**
     * Setting the price paid for the songs
     * @param pricePaid the new price paid for the songs
     */
    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    /**
     * The toString method for easier printing out of OrderSongs
     * @return a String containing all of the details of this OrderSong
     */
    @Override
    public String toString() {
        return "OrderSong{" + "orderId=" + orderId + ", songId=" + songId + ", pricePaid=" + pricePaid + '}';
    }
}
