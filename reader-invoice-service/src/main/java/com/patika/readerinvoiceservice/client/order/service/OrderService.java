package com.patika.readerinvoiceservice.client.order.service;

import com.patika.readerinvoiceservice.client.order.OrderClient;
import com.patika.readerinvoiceservice.client.order.dto.response.OrderResponse;
import com.patika.readerinvoiceservice.dto.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {

    private final OrderClient orderClient;

    public OrderResponse getOrderByCode(String orderCode) {
        GenericResponse<OrderResponse> response = orderClient.getByCode(orderCode);
        if (response == null || !HttpStatus.OK.equals(response.getHttpStatus())) {
            log.error("Error Message: {}", response.getError());
        }
        return response.getData();
    }
}
