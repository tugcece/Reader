package com.patika.readerinvoiceservice.service;

import com.patika.readerinvoiceservice.client.order.dto.response.OrderResponse;
import com.patika.readerinvoiceservice.client.order.service.OrderService;
import com.patika.readerinvoiceservice.converter.InvoiceConverter;
import com.patika.readerinvoiceservice.dto.request.InvoiceSaveRequest;
import com.patika.readerinvoiceservice.exception.ExceptionMessages;
import com.patika.readerinvoiceservice.exception.ReaderException;
import com.patika.readerinvoiceservice.model.Invoice;

import com.patika.readerinvoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final OrderService orderService;
    //private final NotificationProducer notificationProducer;

    public void create(InvoiceSaveRequest request) {
        // Call InvoiceCodeGenerator and generate invoice number and create invoice...
        OrderResponse orderResponse = orderService.getOrderByCode(request.getOrderCode());

        Invoice invoice = InvoiceConverter.convert(request, orderResponse.getOrderCode());

        invoiceRepository.addInvoice(invoice);

      //  notificationProducer.sendNotification(prepareNotificationDto(NotificationType.MAIL, orderResponse, invoice.getInvoiceNo()));
    }

  /*  private NotificationDto prepareNotificationDto(NotificationType type, OrderResponse orderResponse, Long invoiceNo) {
        return NotificationDto.builder()
                .notificationType(type)
                .InvoiceNo(invoiceNo)
                .OrderCode(orderResponse.getOrderCode())
                .build();
    }*/

    public Invoice getInvoiceByNumber(Long invoicenumber) {
        Optional<Invoice> foundInvoice = invoiceRepository.findById(invoicenumber);

        if (foundInvoice.isEmpty()) {
            log.error(ExceptionMessages.INVOICE_NOT_FOUND);
            throw new ReaderException(ExceptionMessages.INVOICE_NOT_FOUND);
        }
        return foundInvoice.get();
    }
}
