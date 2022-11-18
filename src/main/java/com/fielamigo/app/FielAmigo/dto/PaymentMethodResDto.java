package com.fielamigo.app.FielAmigo.dto;

import java.util.Date;

public class PaymentMethodResDto {
    private Integer paymentMethodId;
    private Integer lastDigits;
    private Date expirationDate;
    private String name;

    public PaymentMethodResDto() {
    }

    public PaymentMethodResDto(Integer paymentMethodId, Integer lastDigits, Date expirationDate, String name) {
        this.paymentMethodId = paymentMethodId;
        this.lastDigits = lastDigits;
        this.expirationDate = expirationDate;
        this.name = name;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Integer getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(Integer lastDigits) {
        this.lastDigits = lastDigits;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PaymentMethodResDto [paymentMethodId=" + paymentMethodId + ", lastDigits=" + lastDigits
                + ", expirationDate=" + expirationDate + ", name=" + name + "]";
    }
}
