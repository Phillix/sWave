package Dtos;

import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public class UltimateOrder {

    private final int MERCH_SIZE;
    private final int SONG_SIZE;
    private double total;
    private String dateOrdered;
    private int[] qty;
    private double[] merchPrice;
    private String[] title;
    private int[] songId;
    private double[] songPrice;

    public UltimateOrder() {
        MERCH_SIZE = 0;
        SONG_SIZE  = 0;
        total = 50.0;
        dateOrdered = "date";
        qty = new int[]{0};
        merchPrice = new double[]{20.0};
        title = new String[]{"testcase"};
        songId = new int[]{-1};
        songPrice = new double[]{15.50};
    }

    /**
     * used for when user has only merch in order
     * @param o single order object
     * @param om Collection of orderMerch belonging to the given order
     * @param m Collection of Merch belonging to the given order
     */
    public UltimateOrder(Order o, ArrayList<OrderMerch> om, ArrayList<Merch> m) {

        MERCH_SIZE = m.size();
        SONG_SIZE = 0;
        qty = new int[MERCH_SIZE];
        merchPrice = new double[MERCH_SIZE];
        title = new String[MERCH_SIZE];
        total = o.getTotal();
        dateOrdered = o.getDateOrdered();

        for(int i = 0; i < MERCH_SIZE; i++) {
            title[i] = m.get(i).getTitle();
            qty[i] = om.get(i).getQty();
            merchPrice[i] = om.get(i).getPricePaid();
        }
    }
    
    /**
     * used for when user has only songs in order
     * @param o order the songs belong to
     * @param os the OrderSong list to get data from
     */
    public UltimateOrder(Order o, ArrayList<OrderSong> os) {

        SONG_SIZE = os.size();
        MERCH_SIZE = 0;
        songId = new int[SONG_SIZE];
        songPrice = new double[SONG_SIZE];
        total = o.getTotal();
        dateOrdered = o.getDateOrdered();

        for(int i = 0; i < SONG_SIZE; i++) {
            songId[i] = os.get(i).getSongId();
            songPrice[i] = os.get(i).getPricePaid();
        }
    }
    
    /**
     * used for when user has both songs and merch in order
     * @param o order items belong to
     * @param om Collection of orderMerch belonging to the given order
     * @param m Collection of Merch belonging to the given order
     * @param os Collection of orderSong belonging to the given order
     */
    public UltimateOrder(Order o, ArrayList<OrderMerch> om, ArrayList<Merch> m, ArrayList<OrderSong> os) {

        MERCH_SIZE = m.size();
        SONG_SIZE = os.size();
        qty = new int[MERCH_SIZE];
        merchPrice = new double[MERCH_SIZE];
        title = new String[MERCH_SIZE];
        songId = new int[SONG_SIZE];
        songPrice = new double[SONG_SIZE];
        total = o.getTotal();
        dateOrdered = o.getDateOrdered();
        
        if(SONG_SIZE > MERCH_SIZE) {
            
            for(int i = 0; i < SONG_SIZE; i++) {
                
                songId[i] = os.get(i).getSongId();
                songPrice[i] = os.get(i).getPricePaid();
                
                if(i < MERCH_SIZE) {
                    title[i] = m.get(i).getTitle();
                    qty[i] = om.get(i).getQty();
                    merchPrice[i] = om.get(i).getPricePaid();
                }
            }
        } else if(SONG_SIZE < MERCH_SIZE) {
            
            for(int i = 0; i < MERCH_SIZE; i++) {
                
                title[i] = m.get(i).getTitle();
                qty[i] = om.get(i).getQty();
                merchPrice[i] = om.get(i).getPricePaid();
                
                if(i < SONG_SIZE) {
                    songId[i] = os.get(i).getSongId();
                    songPrice[i] = os.get(i).getPricePaid();
                }
            }
        }
    }

    public int getMerchSize() {
        return MERCH_SIZE;
    }
    
    public int getSongSize() {
        return SONG_SIZE;
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

    public double getMerchPrice(int i) {
        return merchPrice[i];
    }

    public void setMerchPrice(double[] merchPrice) {
        this.merchPrice = merchPrice;
    }
    
    public int getSongId(int i) {
        return songId[i];
    }

    public void setSongId(int[] songId) {
        this.songId = songId;
    }

    public double getSongPrice(int i) {
        return songPrice[i];
    }

    public void setSongPrice(double[] songPrice) {
        this.songPrice = songPrice;
    }

    public String getTitle(int i) {
        return title[i];
    }

    public void setTitle(String[] title) {
        this.title = title;
    }
}