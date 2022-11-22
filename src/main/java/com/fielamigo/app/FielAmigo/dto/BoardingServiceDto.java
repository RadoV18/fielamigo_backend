package com.fielamigo.app.FielAmigo.dto;

public class BoardingServiceDto {
    private Double nightlyRate;
    private Integer maxNumberOfNights;
    private Integer maxNumberOfDogs;
    private Boolean canPickup;
    private Double pickupRate;

    public BoardingServiceDto() {
    }

    public BoardingServiceDto(Double nightlyRate, Integer maxNumberOfNights, Integer maxNumberOfDogs, Boolean canPickup, Double pickupRate) {
        this.nightlyRate = nightlyRate;
        this.maxNumberOfNights = maxNumberOfNights;
        this.maxNumberOfDogs = maxNumberOfDogs;
        this.canPickup = canPickup;
        this.pickupRate = pickupRate;
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

    public Boolean isCanPickup() {
        return this.canPickup;
    }

    public Boolean getCanPickup() {
        return this.canPickup;
    }

    public void setCanPickup(Boolean canPickup) {
        this.canPickup = canPickup;
    }

    public Double getPickupRate() {
        return this.pickupRate;
    }

    public void setPickupRate(Double pickupRate) {
        this.pickupRate = pickupRate;
    }

    @Override
    public String toString() {
        return "{" +
            " nightlyRate='" + getNightlyRate() + "'" +
            ", maxNumberOfNights='" + getMaxNumberOfNights() + "'" +
            ", maxNumberOfDogs='" + getMaxNumberOfDogs() + "'" +
            ", canPickup='" + isCanPickup() + "'" +
            ", pickupRate='" + getPickupRate() + "'" +
            "}";
    }
}
