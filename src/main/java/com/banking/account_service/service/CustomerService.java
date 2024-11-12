package com.banking.account_service.service;

import com.banking.account_service.exception.InvalidRequestException;
import com.banking.account_service.model.Customer;
import com.banking.account_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    private static final String SSN_PATTERN_WITH_DASHES = "^(?!000|666|9\\d\\d)[0-9]{3}-(?!00)[0-9]{2}-(?!0{4})[0-9]{4}$";
    private static final String SSN_PATTERN_NO_DASHES = "^(?!000|666|9\\d\\d)[0-9]{9}$";


    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        String taxId = customer.getTaxId();
        if (!isValidSSN(taxId)) {
            throw new InvalidRequestException("Invalid SSN");
        }
        return customerRepository.save(customer);

    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerByTaxId(String taxId) {
        return customerRepository.findByTaxId(taxId);
    }

    public static boolean isValidSSN(String ssn) {
        if (ssn == null) {
            throw new InvalidRequestException("SSN can not be null");
        }
        return Pattern.matches(SSN_PATTERN_WITH_DASHES, ssn) || Pattern.matches(SSN_PATTERN_NO_DASHES, ssn);
    }
}
