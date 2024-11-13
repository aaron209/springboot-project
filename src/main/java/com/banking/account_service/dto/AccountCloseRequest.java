package com.banking.account_service.dto;

import jakarta.validation.constraints.NotNull;

public class AccountCloseRequest {

    @NotNull
    private String lastName;

    @NotNull
    private String taxId;

    @NotNull
    private String customerId;

    @NotNull
    private String accountNumber;

    public AccountCloseRequest(String lastName, String taxId, String customerId, String accountNumber) {
        this.lastName = lastName;
        this.taxId = taxId;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
