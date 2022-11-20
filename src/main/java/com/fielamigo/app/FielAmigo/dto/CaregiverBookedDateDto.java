package com.fielamigo.app.FielAmigo.dto;

import java.util.List;

import com.fielamigo.app.FielAmigo.utils.DateCount;

public class CaregiverBookedDateDto {

    Integer maxDogs;
    List<DateCount> dates;

    public CaregiverBookedDateDto() {
    }

    public CaregiverBookedDateDto(Integer maxDogs, List<DateCount> dates) {
        this.maxDogs = maxDogs;
        this.dates = dates;
    }

    public Integer getMaxDogs() {
        return maxDogs;
    }

    public void setMaxDogs(Integer maxDogs) {
        this.maxDogs = maxDogs;
    }

    public List<DateCount> getDates() {
        return dates;
    }

    public void setDates(List<DateCount> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "CaregiverBookedDateDto [maxDogs=" + maxDogs + ", dates=" + dates + "]";
    }

}
