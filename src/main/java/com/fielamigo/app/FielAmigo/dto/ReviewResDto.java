package com.fielamigo.app.FielAmigo.dto;

public class ReviewResDto {
    private Integer reviewId;
    private Integer rating;
    private String comments;
    private String firstName;
    private String lastName;

    public ReviewResDto(Integer reviewId, Integer rating, String comments, String firstName, String lastName) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comments = comments;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    @Override
    public String toString() {
        return "ReviewResDto [reviewId=" + reviewId + ", rating=" + rating + ", comments=" + comments + ", firstName="
                + firstName + ", lastName=" + lastName + "]";
    }

}
