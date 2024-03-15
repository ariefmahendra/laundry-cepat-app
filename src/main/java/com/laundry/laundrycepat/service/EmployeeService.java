package com.laundry.laundrycepat.service;

import com.laundry.laundrycepat.dto.EmployeeCreateRequest;
import com.laundry.laundrycepat.entity.EmployeeEntity;
import com.laundry.laundrycepat.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // get employee by id
    @Transactional
    public EmployeeEntity getEmployeeById(String id) {
     return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found"));
    }

    // get employee by phone number
    @Transactional
    public boolean getEmployeeByPhoneNumber(String phoneNumber) {
        Optional<EmployeeEntity> employee = employeeRepository.findByPhoneNumber(phoneNumber);
        return employee.isPresent();
    }

    // get list employee
    @Transactional
    public List<EmployeeEntity> getAll(){
        return employeeRepository.findAll();
    }

    // create employee
    @Transactional
    public EmployeeEntity create(EmployeeCreateRequest payload) {
        boolean employeeByPhoneNumber = getEmployeeByPhoneNumber(payload.getPhoneNumber());
        if (employeeByPhoneNumber) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "employee already exist");
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setName(payload.getName());
        employeeEntity.setAddress(payload.getAddress());
        employeeEntity.setPhoneNumber(payload.getPhoneNumber());

        return employeeRepository.save(employeeEntity);
    }

    // update employee
    @Transactional
    public EmployeeEntity update(EmployeeEntity payload) {
        EmployeeEntity employeeById = getEmployeeById(payload.getId());
        if (employeeById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found");
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setName(payload.getName());
        employeeEntity.setAddress(payload.getAddress());
        employeeEntity.setPhoneNumber(payload.getPhoneNumber());

        return employeeRepository.save(employeeEntity);
    }

    // delete employee by id
    @Transactional
    public void delete(String id){
        getEmployeeById(id);
        employeeRepository.deleteById(id);
    }
}
