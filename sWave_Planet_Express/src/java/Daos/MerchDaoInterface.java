/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Dtos.Merch;
import java.util.ArrayList;

/**
 *
 * @author Phillix
 */
public interface MerchDaoInterface {
    
    public int createMerch(Merch m);
    public ArrayList<Merch> viewMerchAlpha();
}
