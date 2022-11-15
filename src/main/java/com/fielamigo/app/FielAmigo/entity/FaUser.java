package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaUser {
    private Integer userId;
    private String email;
    private String secret;
    private Integer catStatus;
    private Integer status;
    private String txUser;
    private String txHost;
    private Date txDate;

    public FaUser() {
    }

    public FaUser(Integer userId, String email, String secret, Integer catStatus, Integer status, String txUser, String txHost, Date txDate) {
        this.userId = userId;
        this.email = email;
        this.secret = secret;
        this.catStatus = catStatus;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(Integer catStatus) {
        this.catStatus = catStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String gettxUser() {
        return txUser;
    }

    public void settxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public String toString() {
        return "FaUser ["+
            "userId=" + userId +
            ", email=" + email +
            ", secret=" + secret +
            ", catStatus=" + catStatus +
            ", status=" + status +
            ", txUser=" + txUser +
            ", txHost=" + txHost +
            ", txDate=" + txDate
                + "]";
    }

    
    
}
