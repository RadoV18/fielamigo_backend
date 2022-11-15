package com.fielamigo.app.FielAmigo.dto;

public class MailVerificationDto {
    private String cookie;

    public MailVerificationDto(String cookie) {
        this.cookie = cookie;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public String toString() {
        return "MailVerificationDto [cookie=" + cookie + "]";
    }
    
}
