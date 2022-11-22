package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaBoardingService {
    private Integer boardingServiceId;
    private Integer caregiverId;
    private Double nightlyRate;
    private Integer maxNumberOfNights;
    private Integer maxNumberOfDogs;
    private Boolean canPickup;
    private Double PickupRate;
    private Integer status;
    private String tx_host;
    private String tx_user;
    private Date tx_date;


    public FaBoardingService() {
    }

    public FaBoardingService(Integer boardingServiceId, Integer caregiverId, Double nightlyRate, Integer maxNumberOfNights, Integer maxNumberOfDogs, Boolean canPickup, Double PickupRate, Integer status, String tx_host, String tx_user, Date tx_date) {
        this.boardingServiceId = boardingServiceId;
        this.caregiverId = caregiverId;
        this.nightlyRate = nightlyRate;
        this.maxNumberOfNights = maxNumberOfNights;
        this.maxNumberOfDogs = maxNumberOfDogs;
        this.canPickup = canPickup;
        this.PickupRate = PickupRate;
        this.status = status;
        this.tx_host = tx_host;
        this.tx_user = tx_user;
        this.tx_date = tx_date;
    }

    public Integer getBoardingServiceId() {
        return this.boardingServiceId;
    }

    public void setBoardingServiceId(Integer boardingServiceId) {
        this.boardingServiceId = boardingServiceId;
    }

    public Integer getCaregiverId() {
        return this.caregiverId;
    }

    public void setCaregiverId(Integer caregiverId) {
        this.caregiverId = caregiverId;
    }

    public Double getNightlyRate() {
        return this.nightlyRate;
    }

    public void setNightlyRate(Double nightlyRate) {
        this.nightlyRate = nightlyRate;
    }

    public Integer getMaxNumberOfNights() {
        return this.maxNumberOfNights;
    }

    public void setMaxNumberOfNights(Integer maxNumberOfNights) {
        this.maxNumberOfNights = maxNumberOfNights;
    }

    public Integer getMaxNumberOfDogs() {
        return this.maxNumberOfDogs;
    }

    public void setMaxNumberOfDogs(Integer maxNumberOfDogs) {
        this.maxNumberOfDogs = maxNumberOfDogs;
    }

    public Boolean getCanPickup() {
        return this.canPickup;
    }

    public void setCanPickup(Boolean canPickup) {
        this.canPickup = canPickup;
    }

    public Double getPickupRate() {
        return this.PickupRate;
    }

    public void setPickupRate(Double PickupRate) {
        this.PickupRate = PickupRate;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTx_host() {
        return this.tx_host;
    }

    public void setTx_host(String tx_host) {
        this.tx_host = tx_host;
    }

    public String getTx_user() {
        return this.tx_user;
    }

    public void setTx_user(String tx_user) {
        this.tx_user = tx_user;
    }

    public Date getTx_date() {
        return this.tx_date;
    }

    public void setTx_date(Date tx_date) {
        this.tx_date = tx_date;
    }

    @Override
    public String toString() {
        return "{" +
            " boardingServiceId='" + getBoardingServiceId() + "'" +
            ", caregiverId='" + getCaregiverId() + "'" +
            ", nightlyRate='" + getNightlyRate() + "'" +
            ", maxNumberOfNights='" + getMaxNumberOfNights() + "'" +
            ", maxNumberOfDogs='" + getMaxNumberOfDogs() + "'" +
            ", PickupRate='" + getPickupRate() + "'" +
            ", status='" + getStatus() + "'" +
            ", tx_host='" + getTx_host() + "'" +
            ", tx_user='" + getTx_user() + "'" +
            ", tx_date='" + getTx_date() + "'" +
            "}";
    }

}
