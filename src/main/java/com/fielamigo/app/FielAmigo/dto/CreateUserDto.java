package com.fielamigo.app.FielAmigo.dto;

public class CreateUserDto {
    private String email;
    private String password;
    private boolean isOwner;

    public CreateUserDto() {
    }

    public CreateUserDto(String email, String password, boolean isOwner) {
        this.email = email;
        this.password = password;
        this.isOwner = isOwner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    @Override
    public String toString() {
        return "CreateUserDto [email="
            + email
            + ", password=" + password
            + ", isOwner=" + isOwner + "]";
    }
}
