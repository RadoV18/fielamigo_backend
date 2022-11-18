package com.fielamigo.app.FielAmigo.dto;

import java.util.Date;

public class UserDetailsReqDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;

    public UserDetailsReqDto() {
    }

    public UserDetailsReqDto(String name, String lastName, String phoneNumber, Date birhDate) {
        this.firstName = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birhDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
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

    public void setBirthDate(Date birhDate) {
        this.birthDate = birhDate;
    }

    @Override
    public String toString() {
        return "UserDetailsReqDto [name=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
                + ", birhDate=" + birthDate + "]";
    }
}
