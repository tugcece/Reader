package com.patika.readerinvoiceservice.repository;

import com.patika.readerinvoiceservice.model.Invoice;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InvoiceRepository {

    private List<Invoice> invoiceList = new ArrayList<>();

    public void addInvoice(Invoice invoice) {
        invoiceList.add(invoice);
    }
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public Optional<Invoice> findById(Long no) {
        return getInvoiceList().stream()
                .filter(invoice -> invoice.getInvoiceNo().equals(no))
                .findFirst();
    }

}
