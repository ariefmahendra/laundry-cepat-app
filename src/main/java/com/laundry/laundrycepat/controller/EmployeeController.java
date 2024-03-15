package com.laundry.laundrycepat.controller;

import com.laundry.laundrycepat.dto.EmployeeCreateRequest;
import com.laundry.laundrycepat.dto.WebResponse;
import com.laundry.laundrycepat.entity.EmployeeEntity;
import com.laundry.laundrycepat.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get list employee
    @GetMapping("/api/employees")
    public WebResponse<List<EmployeeEntity>> getAll(){
        List<EmployeeEntity> employeeEntities = employeeService.getAll();
        return WebResponse.<List<EmployeeEntity>>builder()
                .message("OK")
                .data(employeeEntities)
                .build();
    }

    // get employee by id
    @GetMapping("/api/employees/{id}")
    public WebResponse<EmployeeEntity> getById(@PathVariable String id){
        EmployeeEntity employeeEntity = employeeService.getEmployeeById(id);
        return WebResponse.<EmployeeEntity>builder()
                .message("OK")
                .data(employeeEntity)
                .build();
    }

    // create employee
    @PostMapping("/api/employees")
    public WebResponse<EmployeeEntity> create(@RequestBody EmployeeCreateRequest payload){
        EmployeeEntity employeeEntity = employeeService.create(payload);
        return WebResponse.<EmployeeEntity>builder()
                .message("employee created")
                .data(employeeEntity)
                .build();
    }

    // update employee
    @PutMapping("/api/employees/{id}")
    public WebResponse<EmployeeEntity> update(@PathVariable String id, @RequestBody EmployeeCreateRequest payload){

        EmployeeEntity request = new EmployeeEntity();
        request.setId(id);
        request.setName(payload.getName());
        request.setAddress(payload.getAddress());
        request.setPhoneNumber(payload.getPhoneNumber());

        EmployeeEntity employeeEntity = employeeService.update(request);
        return WebResponse.<EmployeeEntity>builder()
                .message("OK")
                .data(employeeEntity)
                .build();
    }

    // delete employee
    @DeleteMapping("/api/employees/{id}")
    public WebResponse<String> delete(@PathVariable String id){
        employeeService.delete(id);
        return WebResponse.<String>builder().message("employee deleted").build();
    }
}
