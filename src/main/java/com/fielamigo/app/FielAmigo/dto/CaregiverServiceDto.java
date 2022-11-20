package com.fielamigo.app.FielAmigo.dto;

public class CaregiverServiceDto {
    Integer serviceId;
    String name;
    Double price;

    public CaregiverServiceDto() {
    }

    public CaregiverServiceDto(Integer serviceId, String name, Double price) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CaregiverServiceDto [serviceId=" + serviceId + ", name=" + name + ", price=" + price + "]";
    }

}
