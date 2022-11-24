package com.fielamigo.app.FielAmigo.dto;
import java.sql.Date;
import java.util.List;

public class ReservationInfoDto {
    private Integer boardingReservationId;
    private String serviceType;
    private List<DogDto> dogs;
    
    private Date startDate;
    private Date endDate;
    private Boolean includePickup;
    private String location;   
    private String notes;
    private Double nightlyRate;
    private Double pickupRate;

    public ReservationInfoDto() {
    }

    public ReservationInfoDto(Integer boardingReservationId, String serviceType, List<DogDto> dogs, Date startDate,
    Date endDate, Boolean includePickup, String location, String notes, Double nightlyRate,
            Double pickupRate) {
        this.boardingReservationId = boardingReservationId;
        this.serviceType = serviceType;
        this.dogs = dogs;
        this.startDate = startDate;
        this.endDate = endDate;
        this.includePickup = includePickup;
        this.location = location;
        this.notes = notes;
        this.nightlyRate = nightlyRate;
        this.pickupRate = pickupRate;
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

    public List<DogDto> getDogs() {
        return dogs;
    }

    public void setDogs(List<DogDto> dogs) {
        this.dogs = dogs;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    @Override
    public String toString() {
        return "ReservationInfoDto [boardingReservationId=" + boardingReservationId + ", serviceType=" + serviceType
                + ", dogs=" + dogs + ", startDate=" + startDate + ", endDate=" + endDate + ", includePickup="
                + includePickup + ", location=" + location + ", notes=" + notes + ", nightlyRate=" + nightlyRate
                + ", pickupRate=" + pickupRate + "]";
    }    
}
