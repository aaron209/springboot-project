package com.banking.account_service.controller;

import com.banking.account_service.model.Account;
import com.banking.account_service.response.AccountCreatedResponse;
import com.banking.account_service.response.AccountDetailsResponse;
import com.banking.account_service.response.GetAllAccountsResponse;
import com.banking.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping()
    public ResponseEntity<?> createAccount(@RequestBody Account account, @RequestHeader("Account-Type") String accountType, @RequestHeader("Registration-Type") String registrationType, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Account createdAccount = accountService.createAccount(account, accountType.toUpperCase(), registrationType.toUpperCase());
        return ResponseEntity.status(201).body(new AccountCreatedResponse(createdAccount.getCustomerId(),
                HttpStatus.valueOf(201), createdAccount.getCustomer().getLastName() + "," +
                createdAccount.getCustomer().getFirstName(), createdAccount.getCustomer().getEmail(),
                "Account created successfully", LocalDateTime.now()));
    }

    @GetMapping("/get-all-accounts")
    public List<GetAllAccountsResponse> getAllAccountsByCustomerId(@RequestHeader("Customer-Id") String customerId) {

        List<Account> accounts = accountService.findAllAccountByCustomerId(customerId);

        GetAllAccountsResponse response = null;

        List<GetAllAccountsResponse> accountDetails = new ArrayList<>();
        for (Account account : accounts) {
            AccountDetailsResponse details = new AccountDetailsResponse(account.getAccountType(),
                    account.getRegistrationType(), account.isActive(), account.isRetirement(), account.isService());
            response = new GetAllAccountsResponse(account.getAccountNumber(), account.getProductCode(), details);
            accountDetails.add(response);
        }
        return accountDetails;

    }

    @GetMapping()
    public List<Account> getAllAccounts() {
        return accountService.findAllAccounts();
    }

//    @GetMapping("/{id}")
//    public Optional<Accounts> getUsers(Long id) {
//        return accountService.getAccountById(id);
//    }
//
//    @PostMapping()
//    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDetails accountDetails, @RequestHeader("Account-Type") AccountType accountType, @RequestHeader("Registration-Type") RegistrationType registrationType) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Account-Type", "");
//        headers.set("Registration-Type", "");
//        Accounts createdAccount = accountService.createAccount(accountDetails, accountType, registrationType);
//        return ResponseEntity.status(201).body(createdAccount);
//    }
//
//    @GetMapping("/{id}")
//    public Optional<User> getUsersById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//
//    @GetMapping("/ind/{username}")
//    public User getByUsername(@PathVariable String username) {
//        return userService.getUserByUsername(username);
//    }
//
//    @PostMapping("delete/{id}")
//    public ResponseEntity deleteByID(@PathVariable Long id) {
//        Optional<User> user = userService.getUserById(id);
//        userService.deleteByUserId(id);
//        return ResponseEntity.status(HttpStatus.OK).body(new UserDeletedResponse(user.get().getId(),
//                user.get().getUsername(), "The user is " + "deleted successfully", LocalDateTime.now()));
//    }
}

