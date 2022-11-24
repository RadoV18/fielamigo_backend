package com.fielamigo.app.FielAmigo.dto;

public class CaregiverBookingsDto {
    private String serviceType;
    private String dogName;
    private String caregiverName;
    private String date;

    public CaregiverBookingsDto() {
    }

    public CaregiverBookingsDto(String serviceType, String dogName, String caregiverName, String date) {
        this.serviceType = serviceType;
        this.dogName = dogName;
        this.caregiverName = caregiverName;
        this.date = date;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    @Override
    public String toString() {
        return "CaregiverBookingsDto [serviceType=" + serviceType + ", dogName=" + dogName + ", caregiverName="
                + caregiverName + ", date=" + date + "]";
    }
}
