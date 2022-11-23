package com.fielamigo.app.FielAmigo.dto;

public class OwnerBookingsDto {
    private String serviceType;
    private String dogName;
    private String caregiverName;   
    private String date;
    private String status;

    public OwnerBookingsDto() {
    }

    public OwnerBookingsDto(String serviceType, String dogName, String caregiverName, String date, String status) {
        this.serviceType = serviceType;
        this.dogName = dogName;
        this.caregiverName = caregiverName;
        this.date = date;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OwnerBookingsDto [serviceType=" + serviceType + ", dogName=" + dogName + ", caregiverName="
                + caregiverName + ", date=" + date + ", status=" + status + "]";
    }
}
