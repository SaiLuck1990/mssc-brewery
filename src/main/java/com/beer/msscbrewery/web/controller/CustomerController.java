package com.beer.msscbrewery.web.controller;

import com.beer.msscbrewery.web.model.CustomerDTO;
import com.beer.msscbrewery.web.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

    private CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("customerId")UUID id){
        return new ResponseEntity<>(customerService.getById(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedDTO = customerService.createCustomer(customerDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "api/v1/customer/"+savedDTO.getUuid().toString());
        return new ResponseEntity(httpHeaders,HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody CustomerDTO customerDTO , @PathVariable("customerId") UUID customerId){
        customerService.updateCustomer(customerDTO, customerId);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId){
        customerService.deleteCustomer(customerId);
    }
}
