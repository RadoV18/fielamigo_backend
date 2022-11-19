package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class AvailableCaregiver {
    private Integer caregiverId;
    private Date startingDate;
    private Date endingDate;
    private Integer maxDogs;
    private Integer dogCount;

    public AvailableCaregiver() {
    }

    public AvailableCaregiver(Integer caregiverId, Date startingDate,
        Date endingDate, Integer availableSpaces, Integer dogCount) {
        this.caregiverId = caregiverId;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.maxDogs = availableSpaces;
        this.dogCount = dogCount;
    }

    public Integer getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(Integer caregiverId) {
        this.caregiverId = caregiverId;
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

    public Integer getMaxDogs() {
        return maxDogs;
    }

    public void setMaxDogs(Integer maxDogs) {
        this.maxDogs = maxDogs;
    }

    public Integer getDogCount() {
        return dogCount;
    }

    public void setDogCount(Integer dogCount) {
        this.dogCount = dogCount;
    }

    @Override
    public String toString() {
        return "AvailableCaregiver [caregiverId=" + caregiverId + ", startingDate=" + startingDate + ", endingDate="
                + endingDate + ", maxDogs=" + maxDogs + ", dogCount=" + dogCount + "]";
    }

}
