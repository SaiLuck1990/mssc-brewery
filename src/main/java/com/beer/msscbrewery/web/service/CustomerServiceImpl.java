package com.beer.msscbrewery.web.service;


import com.beer.msscbrewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDTO getById(UUID customerId){
        return CustomerDTO.builder().uuid(UUID.randomUUID())
                .name("Sailakshmi")
                .build();
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder()
                .uuid(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO, UUID customerId) {
        log.debug("updating customer");
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.debug("deleting customer");
    }

}
