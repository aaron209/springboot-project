package com.banking.account_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    @JsonIgnore
    private Long id;

    @Column(name = "customer_id")
    @JsonIgnore
    private String customerId;

    @JsonIgnore
    @Column(name = "account_type")
    private String accountType;

    @JsonIgnore
    @Column(name = "registration_type")
    private String registrationType;

    @JsonIgnore
    @Column(name = "account_number")
    private String accountNumber;

    @JsonIgnore
    @Column(name = "tax_id")
    private String taxId;

    @JsonIgnore
    @Column(name = "product_code")
    private String productCode;

    @JsonIgnore
    @Column(name = "status")
    private boolean isActive;

    @JsonIgnore
    @Column(name = "retirement")
    private boolean isRetirement;

    @Column(name = "advisor_service")
    private boolean isService;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isRetirement() {
        return isRetirement;
    }

    public void setRetirement(boolean retirement) {
        isRetirement = retirement;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
        isService = service;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
