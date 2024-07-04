package com.patika.readerorderservice.client.product.dto.request;

import com.patika.readerorderservice.dto.request.BaseSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductListSearchRequest extends BaseSearchRequest {

    private List<Long> productIds;
}
