package com.fielamigo.app.FielAmigo.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserDto {
    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private String address1;
    private String address2;
    private String zone;
    private Integer status;
    private Double latitude;
    private Double longitude;

    public UserDto() {

    }

    public UserDto(Long userId, String email, String password, String firstName, String lastName, String phoneNumber,
            Date birthDate, String address1, String address2, String zone, Integer status, Double latitude,
            Double longitude) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address1 = address1;
        this.address2 = address2;
        this.zone = zone;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "UserDto [userId=" + userId + ", email=" + email + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + ", address1="
                + address1 + ", address2=" + address2 + ", zone=" + zone + ", status=" + status + ", latitude="
                + latitude + ", longitude=" + longitude + "]";
    }
}
