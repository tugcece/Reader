package com.patika.readerinvoiceservice.client.order;

import com.patika.readerinvoiceservice.client.order.dto.response.OrderResponse;
import com.patika.readerinvoiceservice.dto.response.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "order-service", url = "localhost:8094/api/v1/orders")
public interface OrderClient {
    @GetMapping("/ordercode/{orderCode}")
    GenericResponse<OrderResponse> getByCode(@PathVariable String orderCode);

}
