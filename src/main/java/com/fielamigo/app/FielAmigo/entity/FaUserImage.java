package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaUserImage {
    private Integer userImageId;
    private Integer userId;
    private Integer imageId;
    private Integer status;
    private String txHost;
    private String txUser;
    private Date txDate;

    public FaUserImage() {
    }

    public FaUserImage(Integer userImageId, Integer userId, Integer imageId, Integer status, String txHost,
            String txUser, Date txDate) {
        this.userImageId = userImageId;
        this.userId = userId;
        this.imageId = imageId;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(Integer userImageId) {
        this.userImageId = userImageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return "FaUserImage [userImageId=" + userImageId + ", userId=" + userId + ", imageId=" + imageId + ", status="
                + status + ", txHost=" + txHost + ", txUser=" + txUser + ", txDate=" + txDate + "]";
    }

}
