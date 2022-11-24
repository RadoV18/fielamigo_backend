package com.fielamigo.app.FielAmigo.dto;

public class DogResDto {
    Integer dogId;
    String imageUrl;
    String name;
    String breed;
    String size;
    String birthDate;
    boolean isMale;
    boolean isSterilized;
    String notes;

    public DogResDto() {
    }

    public DogResDto(Integer dogId, String imageUrl, String name, String breed, String size, String birthDate,
            boolean isMale, boolean isSterilized, String notes) {
        this.dogId = dogId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.breed = breed;
        this.size = size;
        this.birthDate = birthDate;
        this.isMale = isMale;
        this.isSterilized = isSterilized;
        this.notes = notes;
    }

    public Integer getDogId() {
        return dogId;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    public boolean isSterilized() {
        return isSterilized;
    }

    public void setSterilized(boolean isSterilized) {
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
        return "DogResDto [dogId=" + dogId + ", imageUrl=" + imageUrl + ", name=" + name + ", breed=" + breed
                + ", size=" + size + ", birthDate=" + birthDate + ", isMale=" + isMale + ", isSterilized="
                + isSterilized + ", notes=" + notes + "]";
    }
}
