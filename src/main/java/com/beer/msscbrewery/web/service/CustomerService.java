package com.beer.msscbrewery.web.service;

import com.beer.msscbrewery.web.model.CustomerDTO;

import java.util.UUID;

public interface CustomerService {

    CustomerDTO getById(UUID customerId);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO, UUID customerId);

    void deleteCustomer(UUID customerId);
}
