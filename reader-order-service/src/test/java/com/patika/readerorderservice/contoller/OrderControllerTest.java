package com.patika.readerorderservice.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.patika.readerorderservice.controller.OrderController;
import com.patika.readerorderservice.dto.request.OrderSaveRequest;
import com.patika.readerorderservice.dto.request.OrderSearchRequest;
import com.patika.readerorderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void save_successfully() throws Exception {

        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(prepareProductSaveRequest());

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/orders")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON));

        //then - assertion
        resultActions.andExpect(status().isCreated());
        verify(orderService, times(1)).createOrder(Mockito.any(OrderSaveRequest.class));
    }

    private OrderSaveRequest prepareProductSaveRequest() {
        OrderSaveRequest request = new OrderSaveRequest();
        request.setCustomerId(1L);
        request.setProductIds(Arrays.asList(1L, 2L, 3L));
        return request;
    }

    @Test
    void search_successfully() throws Exception {

        //given
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(prepareOrderSearchRequest());

        //when - then
        mockMvc.perform(get("/api/v1/orders")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService, times(1)).getAll(Mockito.any(OrderSearchRequest.class));
    }

    private OrderSearchRequest prepareOrderSearchRequest() {
        OrderSearchRequest orderSearchRequest = new OrderSearchRequest();
        orderSearchRequest.setPage(0);
        orderSearchRequest.setSize(0);
        orderSearchRequest.setOrderCode("Q234WEEERD");
        orderSearchRequest.setCustomerId(1L);
        return orderSearchRequest;
    }

}
