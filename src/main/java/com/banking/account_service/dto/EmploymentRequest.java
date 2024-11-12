package com.banking.account_service.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class EmploymentRequest {

    @NotNull
    private String companyName;

    @NotNull
    private String jobTitle;

    @NotNull
    private BigDecimal annualIncome;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }
}
