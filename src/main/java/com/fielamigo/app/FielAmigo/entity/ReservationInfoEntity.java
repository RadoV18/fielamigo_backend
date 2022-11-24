package com.fielamigo.app.FielAmigo.entity;

import java.sql.Date;

public class ReservationInfoEntity {
    private Integer boardingReservationId;
    private Integer dogId;
    private String dogName;
    private Integer breed;
    private Integer size;
    private Date birthDate;
    private Date startingDate;
    private Date endingDate;
    private Double nightlyRate;
    private Double pickupRate;
    private String notes;
    private Boolean includePickup;
    private String location;

    public ReservationInfoEntity() {
    }

    public ReservationInfoEntity(Integer boardingReservationId, Integer dogId, String dogName, Integer breed,
            Integer size, Date birthDate, Date startingDate, Date endingDate, Double nightlyRate, Double pickupRate,
            String notes, Boolean includePickup, String location) {
        this.boardingReservationId = boardingReservationId;
        this.dogId = dogId;
        this.dogName = dogName;
        this.breed = breed;
        this.size = size;
        this.birthDate = birthDate;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.nightlyRate = nightlyRate;
        this.pickupRate = pickupRate;
        this.notes = notes;
        this.includePickup = includePickup;
        this.location = location;
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

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public Integer getBreed() {
        return breed;
    }

    public void setBreed(Integer breed) {
        this.breed = breed;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public Boolean getIncludePickup() {
        return includePickup;
    }

    public void setIncludePickup(Boolean includePickup) {
        this.includePickup = includePickup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ReservationInfoEntity [boardingReservationId=" + boardingReservationId + ", dogId=" + dogId
                + ", dogName=" + dogName + ", breed=" + breed + ", size=" + size + ", birthDate=" + birthDate
                + ", startingDate=" + startingDate + ", endingDate=" + endingDate + ", nightlyRate=" + nightlyRate
                + ", pickupRate=" + pickupRate + ", notes=" + notes + ", includePickup=" + includePickup + "]";
    }
}
