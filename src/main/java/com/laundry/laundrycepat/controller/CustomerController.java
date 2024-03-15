package com.laundry.laundrycepat.controller;

import com.laundry.laundrycepat.dto.CustomerCreateRequest;
import com.laundry.laundrycepat.dto.WebResponse;
import com.laundry.laundrycepat.entity.CustomerEntity;
import com.laundry.laundrycepat.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // create customer
    @PostMapping("/api/customers")
    public WebResponse<CustomerEntity> create(@RequestBody CustomerCreateRequest payload) {
        CustomerEntity save = customerService.save(payload);
        return WebResponse.<CustomerEntity>builder().message("customer created").data(save).build();
    }

    // update customer
    @PutMapping("/api/customers/{id}")
    public WebResponse<CustomerEntity> update(@PathVariable String id, @RequestBody CustomerCreateRequest payload) {
        CustomerEntity request = new CustomerEntity();
        request.setId(id);
        request.setName(payload.getName());
        request.setAddress(payload.getAddress());
        request.setPhoneNumber(payload.getPhoneNumber());

        CustomerEntity update = customerService.update(request);
        return WebResponse.<CustomerEntity>builder().message("OK").data(update).build();
    }

    // get list customer
    @GetMapping("/api/customers")
    public WebResponse<List<CustomerEntity>> getAll() {
        List<CustomerEntity> customers = customerService.getAll();
        return WebResponse.<List<CustomerEntity>>builder().message("OK").data(customers).build();
    }

    // get customer by id
    @GetMapping("/api/customers/{id}")
    public WebResponse<CustomerEntity> getById(@PathVariable String id) {
        CustomerEntity customer = customerService.getById(id);
        return WebResponse.<CustomerEntity>builder().message("OK").data(customer).build();
    }

    // delete customer by id
    @DeleteMapping("/api/customers/{id}")
    public WebResponse<String> delete(@PathVariable String id) {
        customerService.delete(id);
        return WebResponse.<String>builder().message("customer deleted").build();
    }
}
