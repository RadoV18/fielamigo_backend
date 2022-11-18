package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaDogImage {

    private Integer dogImageId;
    private Integer dogId;
    private Integer imageId;
    private Integer status;
    private String txHost;
    private String txUser;
    private Date txDate;

    public FaDogImage() {
    }

    public FaDogImage(Integer dogImageId, Integer dogId, Integer imageId, Integer status, String txHost, String txUser,
            Date txDate) {
        this.dogImageId = dogImageId;
        this.dogId = dogId;
        this.imageId = imageId;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getDogImageId() {
        return dogImageId;
    }

    public void setDogImageId(Integer dogImageId) {
        this.dogImageId = dogImageId;
    }

    public Integer getDogId() {
        return dogId;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public String toString() {
        return "FaDogImage [dogImageId=" + dogImageId + ", dogId=" + dogId + ", imageId=" + imageId + ", status="
                + status + ", txHost=" + txHost + ", txUser=" + txUser + ", txDate=" + txDate + "]";
    }

}
