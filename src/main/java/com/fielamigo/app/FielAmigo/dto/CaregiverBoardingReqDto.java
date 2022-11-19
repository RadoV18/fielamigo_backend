package com.fielamigo.app.FielAmigo.dto;

import java.util.Date;


public class CaregiverBoardingReqDto {


    private Date startingDate;
    private Date endingDate;
    private Integer cityId;
    private Integer dogCount;

    public CaregiverBoardingReqDto() {
    }

    public CaregiverBoardingReqDto(Date startingDate, Date endingDate, Integer cityId, Integer dogCount) {
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.cityId = cityId;
        this.dogCount = dogCount;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDogCount() {
        return dogCount;
    }

    public void setDogCount(Integer dogCount) {
        this.dogCount = dogCount;
    }

    @Override
    public String toString() {
        return "CaregiverBoardingReqDto [startingDate=" + startingDate + ", endingDate=" + endingDate + ", cityId="
                + cityId + ", dogCount=" + dogCount + "]";
    }

}
