package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaDogBoarding {
    private Integer dogBoardingId;
    private Integer boardingReservationId;
    private Integer dogId;
    private Integer status;
    private String txHost;
    private String txUser;
    private Date txDate;

    public FaDogBoarding() {
    }

    public FaDogBoarding(Integer dogBoardingId, Integer boardingReservationId, Integer dogId, Integer status,
            String txHost, String txUser, Date txDate) {
        this.dogBoardingId = dogBoardingId;
        this.boardingReservationId = boardingReservationId;
        this.dogId = dogId;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getDogBoardingId() {
        return dogBoardingId;
    }

    public void setDogBoardingId(Integer dogBoardingId) {
        this.dogBoardingId = dogBoardingId;
    }

    public Integer getBoardingReservationId() {
        return boardingReservationId;
    }

    public void setBoardingReservationId(Integer boardingReservationId) {
        this.boardingReservationId = boardingReservationId;
    }

    public Integer getDogId() {
        return dogId;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
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
        return "FaDogBoarding [dogBoardingId=" + dogBoardingId + ", boardingReservationId=" + boardingReservationId
                + ", dogId=" + dogId + ", status=" + status + ", txHost=" + txHost + ", txUser=" + txUser + ", txDate="
                + txDate + "]";
    }

}
