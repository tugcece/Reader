package com.patika.readerorderservice.client.product.service;

import com.patika.readerorderservice.client.product.ProductClient;
import com.patika.readerorderservice.client.product.dto.request.ProductListSearchRequest;
import com.patika.readerorderservice.client.product.dto.response.ProductResponse;
import com.patika.readerorderservice.dto.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductClient productClient;

    public List<ProductResponse> getProductsList(List <Long> productIdList) {
        GenericResponse<List<ProductResponse>> response = productClient.getByIdList(productIdList);

        if (response == null || !HttpStatus.OK.equals(response.getHttpStatus())) {
            log.error("Error Message: {}", response.getMessage());
        }
        return response.getData();
    }
}
