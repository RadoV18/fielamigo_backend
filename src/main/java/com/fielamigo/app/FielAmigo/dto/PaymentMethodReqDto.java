package com.fielamigo.app.FielAmigo.dto;

import java.util.Date;

public class PaymentMethodReqDto {
    private String name;
    private String numbers;
    private Date expirationDate;

    public PaymentMethodReqDto() {
    }

    public PaymentMethodReqDto(String name, String numbers, Date expirationDate) {
        this.name = name;
        this.numbers = numbers;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "PaymentMethodReqDto [name=" + name + ", numbers=" + numbers + ", expirationDate=" + expirationDate
                + "]";
    }

}
