package com.patika.readerinvoiceservice.converter;

import com.patika.readerinvoiceservice.dto.request.InvoiceSaveRequest;
import com.patika.readerinvoiceservice.model.Invoice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceConverter {

    public static Invoice convert(InvoiceSaveRequest request, String orderCode) {
        return Invoice.builder()
                .createDate(LocalDateTime.now())
                .InvoiceNo(request.getInvoiceNumber())
                .orderCode(orderCode)
                .build();
    }
}