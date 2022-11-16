package com.fielamigo.app.FielAmigo.dto;

public class ResponseDto<T> {
    private T data;
    private String message;
    private boolean successful;

    public ResponseDto() {
    }

    public ResponseDto(T data, String message, boolean successful) {
        this.data = data;
        this.message = message;
        this.successful = successful;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    @Override
    public String toString() {
        return """
            ResponseDto {
                data=%s,
                message='%s',
                successful=%s
            }
            """.formatted(data, message, successful);
    }
}
