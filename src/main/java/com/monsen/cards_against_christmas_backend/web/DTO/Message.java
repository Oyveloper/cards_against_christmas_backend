package com.monsen.cards_against_christmas_backend.web.DTO;

public class Message<T> {

    private T data;
    private String status;
    private String explanation;

    public Message(T data, String status, String explanation) {
        this.data = data;
        this.status = status;
        this.explanation = explanation;
    }

    public Message() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
