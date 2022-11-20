package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class CaregiverBookings {
    private Date startingDate;
    private Date endingDate;
    private Integer dogCount;
    
    public CaregiverBookings() {
    }

    public CaregiverBookings(Date date, Date endingDate, Integer bookingCount) {
        this.startingDate = date;
        this.dogCount = bookingCount;
        this.endingDate = endingDate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date date) {
        this.startingDate = date;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Integer getDogCount() {
        return dogCount;
    }

    public void setDogCount(Integer dogCount) {
        this.dogCount = dogCount;
    }

    @Override
    public String toString() {
        return "CaregiverBookings [startingDate=" + startingDate + ", endingDate=" + endingDate + ", dogCount="
                + dogCount + "]";
    }

}
