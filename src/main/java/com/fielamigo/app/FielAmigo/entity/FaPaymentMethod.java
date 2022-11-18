package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaPaymentMethod {
    private Integer paymentMethodId;
    private Integer userId;
    private String name;
    private Integer lastDigits;
    private Integer catCardType;
    private String numbers;
    private Date expirationDate;
    private Integer status;
    private String txHost;
    private String txUser;
    private Date txDate;

    public FaPaymentMethod() {
    }

    public FaPaymentMethod(Integer paymentMethodId, Integer userId, String name, Integer lastDigits,
            Integer catCardType, String numbers, Date expirationDate, Integer catStatus, String txHost, String txUser,
            Date txDate) {
        this.paymentMethodId = paymentMethodId;
        this.userId = userId;
        this.name = name;
        this.lastDigits = lastDigits;
        this.catCardType = catCardType;
        this.numbers = numbers;
        this.expirationDate = expirationDate;
        this.status = catStatus;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(Integer lastDigits) {
        this.lastDigits = lastDigits;
    }

    public Integer getCatCardType() {
        return catCardType;
    }

    public void setCatCardType(Integer catCardType) {
        this.catCardType = catCardType;
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

    public Integer getCatStatus() {
        return status;
    }

    public void setCatStatus(Integer catStatus) {
        this.status = catStatus;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public String toString() {
        return "FaPaymentMethod [paymentMethodId=" + paymentMethodId + ", userId=" + userId + ", name=" + name
                + ", lastDigits=" + lastDigits + ", catCardType=" + catCardType + ", numbers=" + numbers
                + ", expirationDate=" + expirationDate + ", catStatus=" + status + ", txHost=" + txHost + ", txUser="
                + txUser + ", txDate=" + txDate + "]";
    }

}
