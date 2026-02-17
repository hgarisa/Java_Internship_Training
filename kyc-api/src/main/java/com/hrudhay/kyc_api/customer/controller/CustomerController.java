package com.hrudhay.kyc_api.customer.controller;

import com.hrudhay.kyc_api.customer.dto.CustomerCreateRequest;

import com.hrudhay.kyc_api.customer.dto.CustomerUpdateRequest;

import com.hrudhay.kyc_api.customer.entity.Customer;
import com.hrudhay.kyc_api.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hrudhay.kyc_api.customer.dto.CustomerPrecheckRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/create")
    public Customer create(@Valid @RequestBody CustomerCreateRequest request) {
        return service.createCustomer(request);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAll();
    }


    @GetMapping("/by-id-number/{idNumber}")
    public Customer getByIdNumber(@PathVariable String idNumber) {
        return service.getByIdNumber(idNumber);
    }

    @GetMapping("/by-email")
    public Customer getByEmail(@RequestParam String email) {
        return service.getByEmail(email);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/search")
    public Customer getByFirst_Last_Email(@RequestParam String firstName,
                                          @RequestParam String lastName,
                                          @RequestParam String email) {
        return service.getByFirstLastEmail(firstName, lastName, email);
    }


    @GetMapping("/me")
    public Customer me(@AuthenticationPrincipal Jwt jwt) {
        return service.getMyCustomer(jwt);
    }

    @GetMapping("/{id:\\d+}")
    public Customer getById(@PathVariable Long id) {
        return service.getById(id);
    }


}
