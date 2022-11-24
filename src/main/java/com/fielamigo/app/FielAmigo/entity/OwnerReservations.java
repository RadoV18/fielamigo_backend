package com.fielamigo.app.FielAmigo.entity;

public class OwnerReservations {
    private Integer boardingReservationId;
    private String dogId;
    private String dogName;
    private String caregiverName;
    private String date;
    private String reservationStatus; 
    
    public OwnerReservations() {
    }
    
    public OwnerReservations(Integer boardingReservationId, String dogId, String dogName,
            String caregiverName, String date, String reservationStatus) {
        this.boardingReservationId = boardingReservationId;
        this.dogId = dogId;
        this.dogName = dogName;
        this.caregiverName = caregiverName;
        this.date = date;
        this.reservationStatus = reservationStatus;
    }

    public Integer getBoardingReservationId() {
        return boardingReservationId;
    }

    public void setBoardingReservationId(Integer boardingReservationId) {
        this.boardingReservationId = boardingReservationId;
    }

    public String getDogId() {
        return dogId;
    }

    public void setDogId(String dogId) {
        this.dogId = dogId;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getCaregiverName() {
        return caregiverName;
    }

    public void setCaregiverName(String caregiverName) {
        this.caregiverName = caregiverName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "OwnerReservations [boardingReservationId=" + boardingReservationId
                + ", dogId=" + dogId + ", dogName=" + dogName + ", caregiverName=" + caregiverName + ", date=" + date
                + ", reservationStatus=" + reservationStatus + "]";
    }    
}

