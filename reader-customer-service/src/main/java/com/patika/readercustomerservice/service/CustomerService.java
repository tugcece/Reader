package com.patika.readercustomerservice.service;

import com.patika.readercustomerservice.converter.CustomerConverter;
import com.patika.readercustomerservice.dto.request.CustomerSaveRequest;
import com.patika.readercustomerservice.exception.ExceptionMessages;
import com.patika.readercustomerservice.exception.ReaderException;
import com.patika.readercustomerservice.model.AccountType;
import com.patika.readercustomerservice.model.Customer;
import com.patika.readercustomerservice.producer.NotificationProducer;
import com.patika.readercustomerservice.producer.dto.NotificationDto;
import com.patika.readercustomerservice.producer.dto.enums.NotificationType;
import com.patika.readercustomerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final NotificationProducer notificationProducer;
    @CacheEvict(cacheNames = "customer", allEntries = true)
    public void save(CustomerSaveRequest request) {

        Optional<Customer> foundCustomer = customerRepository.findByEmail(request.getEmail());

        if (foundCustomer.isPresent()) {
            log.error(ExceptionMessages.EMAIL_ALREADY_EXIST);
            throw new ReaderException(ExceptionMessages.EMAIL_ALREADY_EXIST);
        }

        Customer customer = CustomerConverter.toCustomer(request);

        customerRepository.save(customer);

        notificationProducer.sendNotification(prepareNotificationDto(NotificationType.MAIL, customer));

        log.info("customer created. {}", customer.getEmail());
    }

    public List<Customer> getCustomerList() {
        log.info("customer listed. ");
        return customerRepository.findAll();
    }

    public void changeAccountType(String email, AccountType accountType) {

        Optional<Customer> foundCustomer = getCustomerList()
                .stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();

        if (foundCustomer.isPresent()) {
            foundCustomer.get().setAccountType(accountType);
        }
    }
    @Cacheable(value = "customer", cacheNames = "customer")
    public Customer getById(Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);

        if (foundCustomer.isEmpty()) {
            log.error(ExceptionMessages.CUSTOMER_NOT_FOUND);
            throw new ReaderException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }
        log.info("db'den getirildi.");
        return foundCustomer.get();
    }

    public Customer getByEmail(String email) {

        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        if (!foundCustomer.get().getIsActive()) {
            log.error(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
            throw new ReaderException(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
        }
        log.info("customer found. {}", email);
        return foundCustomer.get();
    }
    private NotificationDto prepareNotificationDto(NotificationType type, Customer customer) {
        return NotificationDto.builder()
                .notificationType(type)
                .customerName(customer.getName())
                .customerEmail(customer.getEmail())
                .customerLastName(customer.getSurname())
                .build();
    }

}
