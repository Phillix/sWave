/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dtos;

/**
 *
 * @author Phillix
 */
public class Ad {
    
    private int adId;
    private String adUrl;
    
    public Ad() {
        adId = 0;
        adUrl = "/notFound";
    }

    public Ad(String adUrl) {
        this.adUrl = adUrl;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    @Override
    public String toString() {
        return "Ad{" + "adId=" + adId + ", adUrl=" + adUrl + '}';
    }
    
    
}
