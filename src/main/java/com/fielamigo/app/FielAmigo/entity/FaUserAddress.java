package com.fielamigo.app.FielAmigo.entity;

public class FaUserAddress {
    
    private Integer userAddressId;
    private Integer userId;
    private String address1;
    private String address2;
    private String zone;
    private Integer catCountryId;
    private Integer catCityId;
    private Integer status;
    private Double latitude;
    private Double longitude;
    private String txHost;
    private String txUser;
    private String txDate;
    
    public FaUserAddress() {
    }

    public FaUserAddress(Integer userAddressId, Integer userId, String address1, String address2, String zone,
            Integer catCountryId, Integer catCityId, Integer status, Double latitude, Double longitude, String txHost,
            String txUser, String txDate) {
        this.userAddressId = userAddressId;
        this.userId = userId;
        this.address1 = address1;
        this.address2 = address2;
        this.zone = zone;
        this.catCountryId = catCountryId;
        this.catCityId = catCityId;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
    }

    public Integer getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Integer userAddressId) {
        this.userAddressId = userAddressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getCatCountryId() {
        return catCountryId;
    }

    public void setCatCountryId(Integer catCountryId) {
        this.catCountryId = catCountryId;
    }

    public Integer getCatCityId() {
        return catCityId;
    }

    public void setCatCityId(Integer catCityId) {
        this.catCityId = catCityId;
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

    public String getTxDate() {
        return txDate;
    }

    public void setTxDate(String txDate) {
        this.txDate = txDate;
    }

    @Override
    public String toString() {
        return "FaUserAddress [userAddressId=" + userAddressId + ", userId=" + userId + ", address1=" + address1
                + ", address2=" + address2 + ", zone=" + zone + ", catCountryId=" + catCountryId + ", catCityId="
                + catCityId + ", status=" + status + ", latitude=" + latitude + ", longitude=" + longitude + ", txHost="
                + txHost + ", txUser=" + txUser + ", txDate=" + txDate + "]";
    }
}
