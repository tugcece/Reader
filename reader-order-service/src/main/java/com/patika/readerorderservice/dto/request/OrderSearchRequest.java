package com.patika.readerorderservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderSearchRequest extends BaseSearchRequest{

    private String orderCode;
    private Long customerId;

}
