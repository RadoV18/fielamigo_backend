package com.fielamigo.app.FielAmigo.dto;

public class DogSimpleDto {
    private String dogId;
    private String name;

    public DogSimpleDto() {
    }

    public DogSimpleDto(String dogId, String name) {
        this.dogId = dogId;
        this.name = name;
    }

    public String getDogId() {
        return dogId;
    }

    public void setDogId(String dogId) {
        this.dogId = dogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DogSimpleDto [dogId=" + dogId + ", name=" + name + "]";
    }
}
