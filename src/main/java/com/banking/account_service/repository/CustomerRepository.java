package com.banking.account_service.repository;

import com.banking.account_service.dto.CustomerRequest;
import com.banking.account_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByTaxId(String taxId);


}
