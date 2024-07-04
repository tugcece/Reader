package com.patika.readerorderservice.client.customer;

import com.patika.readerorderservice.client.customer.dto.response.CustomerResponse;
import com.patika.readerorderservice.dto.response.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer-service", url = "localhost:8091/api/v1/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    GenericResponse<CustomerResponse> getById(@PathVariable Long id);

}
