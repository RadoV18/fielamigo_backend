package com.fielamigo.app.FielAmigo.dto;

import java.util.Date;
import java.util.List;

public class BoardingReservationReqDto {
    private Integer boardingServiceId;
    private Date startingDate;
    private Date endingDate;
    private List<Integer> dogs;
    private boolean includePickup;
    private Integer paymentMethodId;
    private String notes;

    public BoardingReservationReqDto() {
    }

    public BoardingReservationReqDto(Integer boardingServiceId, Date startingDate, Date endingDate, List<Integer> dogs,
            boolean includePickup, Integer paymentMethodId, String notes) {
        this.boardingServiceId = boardingServiceId;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.dogs = dogs;
        this.includePickup = includePickup;
        this.paymentMethodId = paymentMethodId;
        this.notes = notes;
    }

    public Integer getBoardingServiceId() {
        return boardingServiceId;
    }

    public void setBoardingServiceId(Integer boardingServiceId) {
        this.boardingServiceId = boardingServiceId;
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

    public List<Integer> getDogs() {
        return dogs;
    }

    public void setDogs(List<Integer> dogs) {
        this.dogs = dogs;
    }

    public boolean getIncludePickup() {
        return includePickup;
    }

    public void setIncludePickup(boolean includePickup) {
        this.includePickup = includePickup;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "BoardingReservationReqDto [boardingServiceId=" + boardingServiceId + ", startingDate=" + startingDate
                + ", endingDate=" + endingDate + ", dogs=" + dogs + ", includePickup=" + includePickup
                + ", paymentMethodId=" + paymentMethodId + ", notes=" + notes + "]";
    }

}
