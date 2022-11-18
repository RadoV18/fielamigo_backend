package com.fielamigo.app.FielAmigo.dto;

public class UserAddressReqDto {
    private String address1;
    private String address2;
    private Integer cityId;
    private Integer countryId;
    private String zone;
    
    public UserAddressReqDto() {
    }

    public UserAddressReqDto(String address1, String address2, Integer cityId, Integer countryId, String zone) {
        this.address1 = address1;
        this.address2 = address2;
        this.cityId = cityId;
        this.countryId = countryId;
        this.zone = zone;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "UserAddressReqDto [address1=" + address1 + ", address2=" + address2 + ", cityId=" + cityId
                + ", countryId=" + countryId + ", zone=" + zone + "]";
    }

}
