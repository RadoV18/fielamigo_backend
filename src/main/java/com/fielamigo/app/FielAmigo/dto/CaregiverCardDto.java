package com.fielamigo.app.FielAmigo.dto;

public class CaregiverCardDto {
    String firstName;
    String lastName;
    String imageUrl;
    String zone;
    String city;
    Integer reviewCount;
    Double rating;
    Double price;
    boolean isVerified;

    public CaregiverCardDto() {
    }

    public CaregiverCardDto(String firstName, String lastName, String imageUrl, String zone, String city,
            Integer reviewCount, Double rating, Double price, boolean isVerified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.zone = zone;
        this.city = city;
        this.reviewCount = reviewCount;
        this.rating = rating;
        this.price = price;
        this.isVerified = isVerified;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "CaregiverCardDto [firstName=" + firstName + ", lastName=" + lastName + ", imageUrl=" + imageUrl
                + ", zone=" + zone + ", city=" + city + ", reviewCount=" + reviewCount + ", rating=" + rating
                + ", price=" + price + ", isVerified=" + isVerified + "]";
    }

}
