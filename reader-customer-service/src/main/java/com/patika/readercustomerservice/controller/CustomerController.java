package com.patika.readercustomerservice.controller;

import com.patika.readercustomerservice.dto.request.CustomerSaveRequest;
import com.patika.readercustomerservice.dto.response.GenericResponse;
import com.patika.readercustomerservice.model.Customer;
import com.patika.readercustomerservice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void save(@RequestBody CustomerSaveRequest request) {
        customerService.save(request);
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getCustomerList();
    }

    @GetMapping("/{id}")
    public GenericResponse<Customer> getById(@PathVariable Long id) {

        Customer customer = customerService.getById(id);

        return GenericResponse.success(customer);
    }

    @GetMapping("/email/{email}")
    public GenericResponse<Customer> getByEmail(@PathVariable String email) {
        Customer customer = customerService.getByEmail(email);
        return GenericResponse.success(customer);
    }

}
