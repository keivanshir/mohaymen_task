package com.example.mohaymen_task.service.implementation;

import com.example.mohaymen_task.dto.CustomerDto;
import com.example.mohaymen_task.dto.Mapper;
import com.example.mohaymen_task.dto.Response;
import com.example.mohaymen_task.entity.Account;
import com.example.mohaymen_task.entity.Customer;
import com.example.mohaymen_task.exception.NotFoundException;
import com.example.mohaymen_task.repository.AccountRepository;
import com.example.mohaymen_task.repository.CustomerRepository;
import com.example.mohaymen_task.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final Mapper mapper;

    public CustomerServiceImplementation(CustomerRepository customerRepository, AccountRepository accountRepository, Mapper mapper) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public Response<CustomerDto> createCustomer(CustomerDto customerDto) {
        Customer customer = mapper.mapToCustomer(customerDto);
        return Response.<CustomerDto>builder()
                .data(mapper.mapToCustomerDto(customerRepository.save(customer)))
                .message("مشتری ثبت شد!")
                .statusCode(201)
                .build();
    }


    @Override
    public Response<String> deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("مشتری پیدا نشد!"));
        customerRepository.delete(customer);
        return Response.<String>builder()
                .message("مشتری حذف شد!")
                .statusCode(200)
                .build();
    }
}
