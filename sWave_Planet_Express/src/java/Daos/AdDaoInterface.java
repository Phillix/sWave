package Daos;

import Dtos.Ad;

/**
 *
 * @author Phillix
 */
public interface AdDaoInterface {
    public Ad getAd(int id);
    public int getMaxAdId();
}
