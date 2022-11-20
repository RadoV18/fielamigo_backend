package com.fielamigo.app.FielAmigo.entity;

public class BoardingRate {
    private Double nightlyRate;
    private Double pickupRate;

    public BoardingRate() {
    }

    public BoardingRate(Double nightlyRate, Double pickupRate) {
        this.nightlyRate = nightlyRate;
        this.pickupRate = pickupRate;
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
        return "BoardingRate [nightlyRate=" + nightlyRate + ", pickupRate=" + pickupRate + "]";
    }

}
