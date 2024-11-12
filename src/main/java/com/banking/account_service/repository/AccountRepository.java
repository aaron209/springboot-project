package com.banking.account_service.repository;

import com.banking.account_service.dto.AccountRequest;
import com.banking.account_service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByTaxId(String taxId);

    List<Account> findByCustomerId(String customerId);

}
