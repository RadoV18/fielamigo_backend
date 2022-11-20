package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaBoardingReservation {
    private Integer boardingReservationId;
    private Integer boardingServiceId;
    private String paypalOrderId;
    private Date startingDate;
    private Date endingDate;
    private Double nightlyRate;
    private boolean includePickup;
    private Double pickupRate;
    private String notes;
    private Integer catStatus;
    private String txHost;
    private String txUser;
    private Date txDate;

    public FaBoardingReservation() {
    }

    public FaBoardingReservation(Integer boardingReservationId, Integer boardingServiceId, String paypalOrderId,
            Date startingDate, Date endingDate, boolean includePickup, Double nightlyRate, Double pickupRate,
                String notes, Integer catStatus, String txHost, String txUser, Date txDate) {
        this.boardingReservationId = boardingReservationId;
        this.boardingServiceId = boardingServiceId;
        this.paypalOrderId = paypalOrderId;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.nightlyRate = nightlyRate;
        this.includePickup = includePickup;
        this.pickupRate = pickupRate;
        this.notes = notes;
        this.catStatus = catStatus;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getBoardingReservationId() {
        return boardingReservationId;
    }

    public void setBoardingReservationId(Integer boardingReservationId) {
        this.boardingReservationId = boardingReservationId;
    }

    public Integer getBoardingServiceId() {
        return boardingServiceId;
    }

    public void setBoardingServiceId(Integer boardingServiceId) {
        this.boardingServiceId = boardingServiceId;
    }

    public String getPaypalOrderId() {
        return paypalOrderId;
    }

    public void setPaypalOrderId(String paypalOrderId) {
        this.paypalOrderId = paypalOrderId;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Double getNightlyRate() {
        return nightlyRate;
    }

    public void setNightlyRate(Double nightlyRate) {
        this.nightlyRate = nightlyRate;
    }

    public boolean getIncludePickup() {
        return includePickup;
    }

    public void setIncludePickup(boolean includePickup) {
        this.includePickup = includePickup;
    }

    public Double getPickupRate() {
        return pickupRate;
    }

    public void setPickupRate(Double pickupRate) {
        this.pickupRate = pickupRate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(Integer catStatus) {
        this.catStatus = catStatus;
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
        return "FaBoardingReservation [boardingReservationId=" + boardingReservationId + ", boardingServiceId="
                + boardingServiceId + ", paypalOrderId=" + paypalOrderId + ", startingDate=" + startingDate
                + ", endingDate=" + endingDate + ", nightlyRate=" + nightlyRate + ", pickupRate=" + pickupRate
                + ", notes=" + notes + ", catStatus=" + catStatus + ", txHost=" + txHost + ", txUser=" + txUser
                + ", txDate=" + txDate + "]";
    }

}
