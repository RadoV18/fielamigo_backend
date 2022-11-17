package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaDog {
    private Integer dogId;
    private Integer userId;
    private String name;
    private boolean isMale;
    private Date birthDate;
    private Integer catSize;
    private Integer catBreed;
    private boolean isSterilized;
    private String notes;
    private Integer status;
    private String txHost;
    private String txUser;
    private Date txDate;

    public FaDog() {
    }

    public FaDog(Integer dogId, Integer userId, String name, boolean isMale, Date birthDate, Integer catSize,
            Integer catBreed, boolean isSterilized, String notes, Integer status, String txHost, String txUser,
            Date txDate) {
        this.dogId = dogId;
        this.userId = userId;
        this.name = name;
        this.isMale = isMale;
        this.birthDate = birthDate;
        this.catSize = catSize;
        this.catBreed = catBreed;
        this.isSterilized = isSterilized;
        this.notes = notes;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getDogId() {
        return dogId;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getCatSize() {
        return catSize;
    }

    public void setCatSize(Integer catSize) {
        this.catSize = catSize;
    }

    public Integer getCatBreed() {
        return catBreed;
    }

    public void setCatBreed(Integer catBreed) {
        this.catBreed = catBreed;
    }

    public boolean isSterilized() {
        return isSterilized;
    }

    public void setIsSterilized(boolean isSterilized) {
        this.isSterilized = isSterilized;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        return "FaDog [dogId=" + dogId + ", userId=" + userId + ", name=" + name + ", isMale=" + isMale + ", birthDate="
                + birthDate + ", catSize=" + catSize + ", catBreed=" + catBreed + ", isSterilized=" + isSterilized
                + ", notes=" + notes + ", status=" + status + ", txHost=" + txHost + ", txUser=" + txUser + ", txDate="
                + txDate + "]";
    }
}
