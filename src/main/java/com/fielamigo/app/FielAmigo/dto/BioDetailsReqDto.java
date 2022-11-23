package com.fielamigo.app.FielAmigo.dto;

import java.util.List;

public class BioDetailsReqDto {
    private String bio;
    private List<String> houseFeatures;
    private List<String> experience;

    public BioDetailsReqDto() {
    }

    public BioDetailsReqDto(String bio, List<String> houseFeatures, List<String> experience) {
        this.bio = bio;
        this.houseFeatures = houseFeatures;
        this.experience = experience;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getHouseFeatures() {
        return houseFeatures;
    }

    public void setHouseFeatures(List<String> houseFeatures) {
        this.houseFeatures = houseFeatures;
    }

    public List<String> getExperience() {
        return experience;
    }

    public void setExperience(List<String> experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "BioDetailsReqDto [bio=" + bio + ", houseFeatures=" + houseFeatures + ", experience=" + experience + "]";
    }

}
