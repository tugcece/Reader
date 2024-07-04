package com.patika.readerinvoiceservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Invoice {

    private LocalDateTime createDate;
    private Long InvoiceNo;
    private String orderCode;
}
