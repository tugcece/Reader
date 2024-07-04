package com.patika.readerinvoiceservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceSaveRequest {

    private Long invoiceNumber;
    private String orderCode;
}
