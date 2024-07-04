package com.patika.readerorderservice.service;

import com.patika.readerorderservice.client.customer.dto.response.CustomerResponse;
import com.patika.readerorderservice.client.customer.service.CustomerService;
import com.patika.readerorderservice.client.product.dto.response.ProductResponse;
import com.patika.readerorderservice.client.product.service.ProductService;
import com.patika.readerorderservice.converter.OrderConverter;
import com.patika.readerorderservice.dto.request.OrderSaveRequest;
import com.patika.readerorderservice.dto.request.OrderSearchRequest;
import com.patika.readerorderservice.dto.response.OrderResponse;
import com.patika.readerorderservice.model.Order;
import com.patika.readerorderservice.model.spesification.OrderSpecification;
import com.patika.readerorderservice.producer.NotificationProducer;
import com.patika.readerorderservice.producer.dto.NotificationDto;
import com.patika.readerorderservice.producer.dto.ProductDto;
import com.patika.readerorderservice.producer.dto.enums.NotificationType;
import com.patika.readerorderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final NotificationProducer notificationProducer;

    public Set<OrderResponse> getAll(OrderSearchRequest orderSearchRequest) {
        Specification<Order> orderSpecification = OrderSpecification.initProductSpecification(orderSearchRequest);

        PageRequest pageRequest = PageRequest.of(orderSearchRequest.getPage(), orderSearchRequest.getSize());

        Page<Order> orders = orderRepository.findAll(orderSpecification,pageRequest);

        log.info("order getirildi.");

        return OrderConverter.toResponse(orders.stream().toList());
    }

    public void createOrder(OrderSaveRequest request) {
        // Call OrderCodeGenerator and generate order code and return new order...
        CustomerResponse customerResponse = customerService.getCustomerById(request.getCustomerId());
        List<ProductResponse> productList = productService.getProductsList(request.getProductIds());

        Order order = OrderConverter.convert(request, customerResponse, productList);

        orderRepository.save(order);

        notificationProducer.sendNotification(prepareNotificationDto(NotificationType.MAIL, productList));
    }
    private NotificationDto prepareNotificationDto(NotificationType type, List<ProductResponse> productList) {
        return NotificationDto.builder()
                .notificationType(type)
                .productDtoList(prepareProductDtoList(productList))
                .build();
    }

    private List<ProductDto> prepareProductDtoList(List<ProductResponse> productList) {
        return productList
                .stream()
                .map(this::prepareProductDto)
                .toList();
    }

    private ProductDto prepareProductDto(ProductResponse productResponse) {
        return ProductDto.builder()
                .name(productResponse.getName())
                .desc(productResponse.getDescription())
                .build();
    }

}