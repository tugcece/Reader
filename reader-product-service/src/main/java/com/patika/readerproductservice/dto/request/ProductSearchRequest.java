package com.patika.readerproductservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRequest extends BaseSearchRequest {

    private String name;
    private BigDecimal amount;
    private String publisherName;
    private String sortBy;
    private String sortDirection;
    private List<Long> productIds;
}
