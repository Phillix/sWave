package Daos;

import Dtos.Merch;
import Dtos.OrderMerch;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface MerchDaoInterface {
    public int createMerch(Merch m);
    public ArrayList<Merch> viewMerchAlpha();
    public Merch getMerchById(int merchid);
    public ArrayList<Merch> getMerchInOrder(ArrayList<OrderMerch> ids);
    public ArrayList<Merch> searchMerch(String searchWord);
    public int removeMerch(int id);
}
