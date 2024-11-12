package com.banking.account_service.response;

public class AccountDetailsResponse {

    private String accountType;

    private String registrationType;

    private boolean statusCode;

    private boolean isRetirement;

    private boolean isAdvisorService;

    public AccountDetailsResponse(String accountType, String registrationType, boolean statusCode, boolean isRetirement, boolean isAdvisorService) {
        this.accountType = accountType;
        this.registrationType = registrationType;
        this.statusCode = statusCode;
        this.isRetirement = isRetirement;
        this.isAdvisorService = isAdvisorService;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public boolean isStatusCode() {
        return statusCode;
    }

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isRetirement() {
        return isRetirement;
    }

    public void setRetirement(boolean retirement) {
        isRetirement = retirement;
    }

    public boolean isAdvisorService() {
        return isAdvisorService;
    }

    public void setAdvisorService(boolean advisorService) {
        isAdvisorService = advisorService;
    }
}


