package com.fielamigo.app.FielAmigo.entity;

import java.util.Date;

public class FaUserDetails {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private Integer status;
    private String txUser;
    private String txHost;
    private Date txDate;

    public FaUserDetails() {
    }

    public FaUserDetails(Integer userId, String firstName, String lastName, String phoneNumber, Date birthDate,
            Integer status, String txUser, String txHost, Date txDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public String toString() {
        return "FaUserDetails [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + ", status=" + status + ", txUser="
                + txUser + ", txHost=" + txHost + ", txDate=" + txDate + "]";
    }

}
