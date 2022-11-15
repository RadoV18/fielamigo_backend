package com.fielamigo.app.FielAmigo.dto;

public class VerificationCodeResDto {
    String message;

    public VerificationCodeResDto() {
    }

    public VerificationCodeResDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
