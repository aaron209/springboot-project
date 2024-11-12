package com.banking.account_service.response;

public class GetAllAccountsResponse {

    private String accountNumber;

    private String productCode;

    private AccountDetailsResponse accountDetails;

    public GetAllAccountsResponse(String accountNumber, String productCode, AccountDetailsResponse accountDetails) {
        this.accountNumber = accountNumber;
        this.productCode = productCode;
        this.accountDetails = accountDetails;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public AccountDetailsResponse getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetailsResponse accountDetails) {
        this.accountDetails = accountDetails;
    }


}
