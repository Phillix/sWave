/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
