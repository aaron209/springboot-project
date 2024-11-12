package com.banking.account_service.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class AccountCreatedResponse {

    private String id;

    private HttpStatus status;

    private String name;

    private String email;

    private String message;

    private LocalDateTime timestamp;

    public AccountCreatedResponse(String id, HttpStatus status, String name, String email, String message, LocalDateTime timestamp) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.email = email;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
