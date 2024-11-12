package com.banking.account_service.service;

import com.banking.account_service.exception.InvalidRequestException;
import com.banking.account_service.model.Account;
import com.banking.account_service.model.Customer;
import com.banking.account_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    private static final String CHECKING_PRODUCT_CODE = "6400";
    private static final String SAVING_PRODUCT_CODE = "3000";
    private static final String RETIREMENT_PRODUCT_CODE = "IRA";
    private static final String INVESTMENT_PRODUCT_CODE = "INVEST";
    private static final int CHECKING_SAVING_ACCOUNT_DIGITS = 12;
    private static final int RETIRED_ACCOUNT_DIGITS = 10;
    private static final int INVESTMENT_ACCOUNT_DIGITS = 8;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public Account createAccount(Account account, String accountType, String registrationType) {

        account.setCustomerId(generateCustomerId(account.getCustomer().getTaxId()));
        validateRegistrationType(accountType, registrationType);
        account.setAccountNumber(generateAccountNum(accountType));
        Customer taxID = customerService.createCustomer(account.getCustomer());
        account.setTaxId(taxID.getTaxId());
        if (calculateAge(account) < 16) {
            throw new InvalidRequestException("The customer is minor, can not open this account");
        }
        account.setActive(true);
        account.setAccountType(accountType);
        account.setRegistrationType(registrationType);
        setProductCodeAndRetirement(account);

        return accountRepository.save(account);
    }

    public List<Account> findAllAccountByCustomerId(String id) {
        return accountRepository.findByCustomerId(id);
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public String generateCustomerId(String taxId) {

        Customer existingCustomer = customerService.findCustomerByTaxId(taxId);

        String customerId;
        if (existingCustomer == null) {
            Random random = new Random();
            int num = 100000000 + random.nextInt(900000000);
            customerId = String.valueOf(num);
        } else {
            Account accountCreated = accountRepository.findByTaxId(existingCustomer.getTaxId());
            customerId = accountCreated.getCustomerId();
        }
        return customerId;
    }

    public String generateAccountNum(String accountType) {

        String prefix;
        int totalDigits;
        switch (accountType.toUpperCase()) {
            case "CHECKING" -> {
                prefix = CHECKING_PRODUCT_CODE;
                totalDigits = CHECKING_SAVING_ACCOUNT_DIGITS;
            }
            case "SAVING" -> {
                prefix = SAVING_PRODUCT_CODE;
                totalDigits = CHECKING_SAVING_ACCOUNT_DIGITS;
            }
            case "RETIREMENT" -> {
                prefix = "";
                totalDigits = RETIRED_ACCOUNT_DIGITS;
            }
            case "INVESTMENT" -> {
                prefix = "";
                totalDigits = INVESTMENT_ACCOUNT_DIGITS;
            }
            default -> throw new InvalidRequestException("Invalid account type");
        }
        Random random = new Random();
        StringBuilder builder = new StringBuilder(prefix);
        int remainingDigit = totalDigits - prefix.length();

        for (int i = 0; i < remainingDigit; i++) {
            builder.append(random.nextInt(10));

        }
        return builder.toString();
    }

    public void validateRegistrationType(String accountType, String registrationType) {

        switch (accountType.toUpperCase()) {
            case "CHECKING":
            case "SAVING":
                if (!(registrationType.equalsIgnoreCase("IN") || registrationType.equalsIgnoreCase("JT") || registrationType.equalsIgnoreCase("CP"))) {
                    throw new InvalidRequestException("Invalid registration type");
                }
                break;
            case "RETIREMENT":
                if (!(registrationType.equalsIgnoreCase("RH") || registrationType.equalsIgnoreCase("RO"))) {
                    throw new InvalidRequestException("Invalid registration type");
                }
                break;
            case "INVESTMENT":
                if (!(registrationType.equalsIgnoreCase("IN") || registrationType.equalsIgnoreCase("JT"))) {
                    throw new InvalidRequestException("Invalid registration type");
                }
                break;
        }
    }

    public void setProductCodeAndRetirement(Account account) {

        String accountType = account.getAccountType();
        if (accountType.equalsIgnoreCase("CHECKING")) {
            account.setProductCode(CHECKING_PRODUCT_CODE);
            account.setRetirement(false);

        } else if (accountType.equalsIgnoreCase("SAVING")) {
            account.setProductCode(SAVING_PRODUCT_CODE);
            account.setRetirement(false);

        } else if (accountType.equalsIgnoreCase("RETIREMENT")) {
            account.setProductCode(RETIREMENT_PRODUCT_CODE);
            account.setRetirement(true);

        } else if (accountType.equalsIgnoreCase("INVESTMENT")) {
            account.setProductCode(INVESTMENT_PRODUCT_CODE);
            account.setRetirement(false);
        }

    }

    public int calculateAge(Account account) {
        LocalDate dateOfBirth = account.getCustomer().getDateOfBirth();
        if (dateOfBirth != null) {
            return Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
        return 0;
    }

//    @Transactional
//    public Accounts createAccount(AccountDetails accountDetails, AccountType accountType, RegistrationType registrationType) {
//        String accountNumber;
//        if (!customerRepository.findById(accountDetails.getAccount().getCustomer().getCustomerId()).isPresent()) {
//            throw new NotFoundException("The customer is not found with id " + accountDetails.getAccount().getCustomer().getCustomerId());
//        }
//        do {
//            accountNumber = generateAccountNum(accountType);
//        } while (accountRepository.existsByAccountNumber(accountNumber));
//
//    }
//
//    private String generateAccountNum(AccountType accountType) {
//
//        String prefix = null;
//        int totalDigit = 0;
//        switch (accountType) {
//            case CHECKING:
//                prefix = AccountType.CHECKING.getPrefix();
//                totalDigit = AccountType.CHECKING.getLength();
//                break;
//            case SAVING:
//                prefix = AccountType.SAVING.getPrefix();
//                totalDigit = AccountType.SAVING.getLength();
//                break;
//            case RETIRED:
//                prefix = AccountType.RETIRED.getPrefix();
//                totalDigit = AccountType.RETIRED.getLength();
//                break;
//        }
//        Random random = new Random();
//        StringBuilder builder = new StringBuilder(prefix);
//        int remainingDigit = totalDigit - prefix.length();
//
//        for (int i = 0; i < remainingDigit; i++) {
//            builder.append(random.nextInt(10));
//        }
//        return builder.toString();
//    }

}


