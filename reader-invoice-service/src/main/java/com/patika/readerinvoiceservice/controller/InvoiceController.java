package com.patika.readerinvoiceservice.controller;

import com.patika.readerinvoiceservice.dto.request.InvoiceSaveRequest;
import com.patika.readerinvoiceservice.dto.response.GenericResponse;
import com.patika.readerinvoiceservice.model.Invoice;
import com.patika.readerinvoiceservice.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class InvoiceController {

   private final InvoiceService invoiceService;

   @GetMapping("/invoicenumber/{invoicenumber}")
   public GenericResponse<Invoice> getInvoiceById(@PathVariable Long invoicenumber) {
       Invoice invoice = invoiceService.getInvoiceByNumber(invoicenumber);
       return GenericResponse.success(invoice);
   }
    @PostMapping
    public void create(@RequestBody InvoiceSaveRequest request){
        invoiceService.create(request);
    }

}
