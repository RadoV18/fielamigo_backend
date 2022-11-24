package com.fielamigo.app.FielAmigo.dto;

public class CaregiverInfoDto {
    private String caregiverName;
    private String ratingScale;
    private String comments;
    private String location;
    private String BoardingReservationId;
    
    public CaregiverInfoDto() {
    }

    public CaregiverInfoDto(String caregiverName, String ratingScale, String comments, String location,
            String boardingReservationId) {
        this.caregiverName = caregiverName;
        this.ratingScale = ratingScale;
        this.comments = comments;
        this.location = location;
        BoardingReservationId = boardingReservationId;
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

    public String getBoardingReservationId() {
        return BoardingReservationId;
    }

    public void setBoardingReservationId(String boardingReservationId) {
        BoardingReservationId = boardingReservationId;
    }

    @Override
    public String toString() {
        return "CaregiverInfoDto [caregiverName=" + caregiverName + ", ratingScale=" + ratingScale + ", comments="
                + comments + ", location=" + location + ", BoardingReservationId=" + BoardingReservationId + "]";
    }    
}
