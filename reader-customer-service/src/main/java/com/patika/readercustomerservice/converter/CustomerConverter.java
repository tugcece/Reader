package com.patika.readercustomerservice.converter;


import com.patika.readercustomerservice.dto.request.CustomerSaveRequest;
import com.patika.readercustomerservice.model.AccountType;
import com.patika.readercustomerservice.model.Customer;
import com.patika.readercustomerservice.util.HashUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConverter {

    public static Customer toCustomer(CustomerSaveRequest request) {
        String hashedPassword = HashUtil.generate(request.getPassword());

        Customer customer = new Customer(request.getName(), request.getSurname(), request.getEmail(), hashedPassword);
        customer.setId(1L);
        customer.setAccountType(AccountType.STANDARD);
        customer.setIsActive(Boolean.TRUE);
        return customer;
    }
}
