package com.asmeydan.crud.service;

import com.asmeydan.crud.model.Customer;
import com.asmeydan.crud.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final StringEncryptor stringEncryptor;

    public CustomerService(CustomerRepository customerRepository, StringEncryptor stringEncryptor) {
        this.customerRepository = customerRepository;
        this.stringEncryptor = stringEncryptor;
    }

    public Optional<Customer> findCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public List findAll() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        String encryptedPass = stringEncryptor.encrypt(customer.getPassword().trim());
        customer.setPassword(encryptedPass);
        return (Customer) customerRepository.save(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
