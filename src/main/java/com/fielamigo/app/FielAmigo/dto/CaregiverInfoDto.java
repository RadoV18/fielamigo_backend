package com.fielamigo.app.FielAmigo.dto;

public class CaregiverInfoDto {
    private String imageUrl;
    private String caregiverName;
    private String ratingScale;
    private String comments;
    private String location;
    private Integer boardingReservationId;
    
    public CaregiverInfoDto() {
    }

    public CaregiverInfoDto(String imageUrl, String caregiverName, String ratingScale, String comments, String location,
    Integer boardingReservationId) {
        this.imageUrl = imageUrl;
        this.caregiverName = caregiverName;
        this.ratingScale = ratingScale;
        this.comments = comments;
        this.location = location;
        this.boardingReservationId = boardingReservationId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCaregiverName() {
        return caregiverName;
    }

    public void setCaregiverName(String caregiverName) {
        this.caregiverName = caregiverName;
    }

    public String getRatingScale() {
        return ratingScale;
    }

    public void setRatingScale(String ratingScale) {
        this.ratingScale = ratingScale;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getBoardingReservationId() {
        return boardingReservationId;
    }

    public void setBoardingReservationId(Integer boardingReservationId) {
        this.boardingReservationId = boardingReservationId;
    }

    @Override
    public String toString() {
        return "CaregiverInfoDto [imageUrl=" + imageUrl + ", caregiverName=" + caregiverName + ", ratingScale="
                + ratingScale + ", comments=" + comments + ", location=" + location + ", boardingReservationId="
                + boardingReservationId + "]";
    }    
}
