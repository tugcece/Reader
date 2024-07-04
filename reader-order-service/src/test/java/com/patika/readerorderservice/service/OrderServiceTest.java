package com.patika.readerorderservice.service;

import com.patika.readerorderservice.client.customer.dto.response.CustomerResponse;
import com.patika.readerorderservice.client.customer.service.CustomerService;
import com.patika.readerorderservice.client.product.dto.response.ProductResponse;
import com.patika.readerorderservice.client.product.service.ProductService;
import com.patika.readerorderservice.dto.request.OrderSaveRequest;
import com.patika.readerorderservice.dto.request.OrderSearchRequest;
import com.patika.readerorderservice.dto.response.OrderResponse;
import com.patika.readerorderservice.model.Order;
import com.patika.readerorderservice.producer.NotificationProducer;
import com.patika.readerorderservice.producer.dto.NotificationDto;
import com.patika.readerorderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private ProductService productService;

    @Mock
    private NotificationProducer notificationProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        OrderSearchRequest orderSearchRequest = new OrderSearchRequest();
        orderSearchRequest.setPage(0);
        orderSearchRequest.setSize(10);

        Order order = new Order();
        Page<Order> orderPage = new PageImpl<>(Collections.singletonList(order));

        when(orderRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(orderPage);

        Set<OrderResponse> orderResponses = orderService.getAll(orderSearchRequest);

        assertEquals(1, orderResponses.size());
        verify(orderRepository, times(1)).findAll(any(Specification.class), any(PageRequest.class));
    }

    @Test
    void testCreateOrder() {
        OrderSaveRequest request = new OrderSaveRequest();
        request.setCustomerId(1L);
        request.setProductIds(List.of(1L, 2L, 3L));

        CustomerResponse customerResponse = new CustomerResponse();
        List<ProductResponse> productList = List.of(
                new ProductResponse(1L, "Product 1", "Description 1"),
                new ProductResponse(2L, "Product 2", "Description 2"),
                new ProductResponse(3L, "Product 3", "Description 3")
        );

        when(customerService.getCustomerById(anyLong())).thenReturn(customerResponse);
        when(productService.getProductsList(anyList())).thenReturn(productList);

        orderService.createOrder(request);

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(notificationProducer, times(1)).sendNotification(any(NotificationDto.class));
    }
}
