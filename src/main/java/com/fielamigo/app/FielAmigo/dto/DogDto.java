package com.fielamigo.app.FielAmigo.dto;

import java.util.Date;

public class DogDto {
    private Integer userId;
    private String name;
    private Integer breed;
    private Integer size;
    private Date birthDate;
    private boolean isMale;
    private boolean isSterilized;
    private String notes;
    
    public DogDto() {
    }

    public DogDto(Integer userId, String name, Integer breed, Integer size, Date birthDate, boolean isMale, boolean isSterilized,
            String notes) {
        this.userId = userId;
        this.name = name;
        this.breed = breed;
        this.size = size;
        this.birthDate = birthDate;
        this.isMale = isMale;
        this.isSterilized = isSterilized;
        this.notes = notes;
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

    public Integer getBreed() {
        return breed;
    }

    public void setBreed(Integer breed) {
        this.breed = breed;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    public boolean isSterilized() {
        return isSterilized;
    }

    public void setIsSterilized(boolean isSterilized) {
        this.isSterilized = isSterilized;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "DogDto [userId=" + userId + ", name=" + name + ", breed=" + breed + ", size=" + size + ", birthDate="
                + birthDate + ", isMale=" + isMale + ", isSterilized=" + isSterilized + ", notes=" + notes + "]";
    }

}
