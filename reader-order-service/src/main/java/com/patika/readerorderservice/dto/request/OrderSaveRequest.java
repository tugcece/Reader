package com.patika.readerorderservice.dto.request;

import com.patika.readerorderservice.client.product.dto.request.ProductListSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSaveRequest {
    private Long customerId;
    private List<Long> productIds;
}