package com.fielamigo.app.FielAmigo.dto;

import java.util.regex.Pattern;

import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

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

    public void validate() throws FielAmigoException {
        if (email == null || email.isEmpty()) {
            throw new FielAmigoException("Email is null or empty");
        }
        // check email with regex
        // OWASP email regex
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isEmailValid = Pattern.compile(regexPattern)
            .matcher(email)
            .matches();
        if (!isEmailValid) {
            throw new FielAmigoException("Email is not valid");
        }

        if (password == null || password.isEmpty()) {
            throw new FielAmigoException("Password is null or empty");
        }
        // regex for minimum length of 12 characters
        String regexPasswordPattern = "^(?=.{12,}$).*$";
        boolean isPasswordValid = Pattern.compile(regexPasswordPattern)
            .matcher(password)
            .matches();
        if (!isPasswordValid) {
            throw new FielAmigoException("Password is not valid");
        }
    }

    @Override
    public String toString() {
        return "CreateUserDto [email="
            + email
            + ", password=" + password
            + ", isOwner=" + isOwner + "]";
    }
}
