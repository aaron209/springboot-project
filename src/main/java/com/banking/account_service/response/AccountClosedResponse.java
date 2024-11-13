package com.banking.account_service.response;

import java.time.LocalDateTime;

public class AccountClosedResponse {

    private String accountNumber;

    private String message;

    private LocalDateTime localDateTime;

    public AccountClosedResponse(String accountNumber, String message, LocalDateTime localDateTime) {
        this.accountNumber = accountNumber;
        this.message = message;
        this.localDateTime = localDateTime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
