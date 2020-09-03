package com.beer.msscbrewery.web.mapper;


import com.beer.msscbrewery.domain.Customer;
import com.beer.msscbrewery.web.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
