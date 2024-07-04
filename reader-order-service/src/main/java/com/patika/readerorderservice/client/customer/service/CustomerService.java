package com.patika.readerorderservice.client.customer.service;

import com.patika.readerorderservice.client.customer.CustomerClient;
import com.patika.readerorderservice.client.customer.dto.response.CustomerResponse;
import com.patika.readerorderservice.dto.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerService {

    private final CustomerClient customerClient;

    public CustomerResponse getCustomerById(Long customerId) {
        GenericResponse<CustomerResponse> response = customerClient.getById(customerId);
        if (response == null || !HttpStatus.OK.equals(response.getHttpStatus())) {
            log.error("Error Message: {}", response.getMessage());
        }
        return response.getData();
    }
}
