package com.patika.readerorderservice.controller;

import com.patika.readerorderservice.dto.request.OrderSaveRequest;
import com.patika.readerorderservice.dto.request.OrderSearchRequest;
import com.patika.readerorderservice.dto.response.GenericResponse;
import com.patika.readerorderservice.dto.response.OrderResponse;
import com.patika.readerorderservice.model.Order;
import com.patika.readerorderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

   private final OrderService orderService;

   @PostMapping
   public ResponseEntity<Void> create(@RequestBody OrderSaveRequest request){
        orderService.createOrder(request);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<Set<OrderResponse>>> getAll(@RequestBody OrderSearchRequest request) {
        return new ResponseEntity<>(GenericResponse.success(orderService.getAll(request)), HttpStatus.OK);
    }

}
