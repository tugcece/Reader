package com.patika.readerorderservice.converter;

import com.patika.readerorderservice.client.customer.dto.response.CustomerResponse;
import com.patika.readerorderservice.client.product.dto.response.ProductResponse;
import com.patika.readerorderservice.dto.request.OrderSaveRequest;
import com.patika.readerorderservice.dto.response.OrderResponse;
import com.patika.readerorderservice.model.Order;
import com.patika.readerorderservice.model.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter {

    public static Order convert(OrderSaveRequest request, CustomerResponse customer, List<ProductResponse> productList) {
        return Order.builder()
                .createDate(LocalDateTime.now())
                .customerId(customer)
                .orderStatus(OrderStatus.INITIAL)
                .orderCode("order-code" + request.getCustomerId())
                .build();
    }

    public static OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .customerId(order.getCustomerId())
                .orderCode(order.getOrderCode())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public static Set<OrderResponse> toResponse(List<Order> orders) {
        return orders
                .stream()
                .map(OrderConverter::toResponse)
                .collect(Collectors.toSet());
    }
}