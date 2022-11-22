package com.fielamigo.app.FielAmigo.dto;

import java.util.Date;

import com.fielamigo.app.FielAmigo.utils.DateUtil;

public class DogUserDto {
    private Integer dogId;
    private Integer userId;
    private String name;
    private Date birthDate;
    private String size;
    private String breed;
    private String imageUrl;

    public DogUserDto() {
    }

    public DogUserDto(Integer dogId, Integer userId, String name, Date birthDate, String size,
            String breed, String imageUrl) {
        this.dogId = dogId;
        this.userId = userId;
        this.name = name;
        this.birthDate = birthDate;
        this.size = size;
        this.breed = breed;
        this.imageUrl = imageUrl;
    }

    public Integer getDogId() {
        return dogId;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "DogCatalog [dogId=" + dogId + ", userId=" + userId + ", name=" + name 
                + ", birthDate=" + birthDate + ", size=" + size + ", breed=" + breed + ", imageUrl=" + imageUrl + "]";
    }

    public String toHtmlString() {
        return """
            <div class=\"card\">
                <div>
                    <img src=\"%s\" />
                </div>
                <div class=\"info\">
                    <h2>%s</h2>
                    <h5>%s - %s - %s</h5>
                </div>
            </div>
                """.formatted(imageUrl, name, breed, size, (DateUtil.getAge(birthDate) + " años"));
    }
}
