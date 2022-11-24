package com.fielamigo.app.FielAmigo.dto;
import java.util.List;

public class CaregiverBookingsDto {    
    private Integer boardingReservationId;
    private String serviceType;
    private Integer dogId;
    private List<DogSimpleDto> dogs;
    private String caregiverName;
    private String date;
    private String reservationStatus;

    public CaregiverBookingsDto() {
    }

    public CaregiverBookingsDto(Integer boardingReservationId, String serviceType, Integer dogId,
            List<DogSimpleDto> dogs, String caregiverName, String date, String reservationStatus) {
        this.boardingReservationId = boardingReservationId;
        this.serviceType = serviceType;
        this.dogId = dogId;
        this.dogs = dogs;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getDogId() {
        return dogId;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
    }

    public List<DogSimpleDto> getDogs() {
        return dogs;
    }

    public void setDogs(List<DogSimpleDto> dogs) {
        this.dogs = dogs;
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
        return "CaregiverBookingsDto [boardingReservationId=" + boardingReservationId + ", serviceType=" + serviceType
                + ", dogId=" + dogId + ", dogs=" + dogs + ", caregiverName=" + caregiverName + ", date=" + date
                + ", reservationStatus=" + reservationStatus + "]";
    }
}
