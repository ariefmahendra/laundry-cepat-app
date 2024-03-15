package com.laundry.laundrycepat.service;

import com.laundry.laundrycepat.dto.CustomerCreateRequest;
import com.laundry.laundrycepat.entity.CustomerEntity;
import com.laundry.laundrycepat.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerEntity save(CustomerCreateRequest payload) {
        boolean customerByPhoneNumber = getCustomerByPhoneNumber(payload.getPhoneNumber());

        if (customerByPhoneNumber) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "customer already exist");
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName(payload.getName());
        customerEntity.setAddress(payload.getAddress());
        customerEntity.setPhoneNumber(payload.getPhoneNumber());

        return customerRepository.save(customerEntity);
    }

    @Transactional
    public boolean getCustomerByPhoneNumber(String phoneNumber) {
        Optional<CustomerEntity> byPhoneNumber = customerRepository.findByPhoneNumber(phoneNumber);
        return byPhoneNumber.isPresent();
    }

    @Transactional
    public CustomerEntity update(CustomerEntity customerEntity) {
        customerRepository.findById(customerEntity.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));

        return customerRepository.save(customerEntity);
    }

    @Transactional
    public List<CustomerEntity> getAll(){
        return customerRepository.findAll();
    }

    @Transactional
    public CustomerEntity getById(String id){
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @Transactional
    public void delete(String id){
        getById(id);
        customerRepository.deleteById(id);
    }
}
