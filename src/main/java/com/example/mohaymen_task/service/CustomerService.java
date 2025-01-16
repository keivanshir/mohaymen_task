package com.example.mohaymen_task.service;

import com.example.mohaymen_task.dto.CustomerDto;
import com.example.mohaymen_task.dto.Response;

public interface CustomerService {

    Response<CustomerDto> createCustomer(CustomerDto customerDto);
    Response<String> deleteCustomer(Long customerId);
}
