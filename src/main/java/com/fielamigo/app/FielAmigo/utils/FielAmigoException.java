package com.fielamigo.app.FielAmigo.utils;

public class FielAmigoException extends RuntimeException {
    public FielAmigoException(String message) {
        super(message);
    }

    public FielAmigoException(String message, Throwable cause) {
        super(message, cause);
    }
}
