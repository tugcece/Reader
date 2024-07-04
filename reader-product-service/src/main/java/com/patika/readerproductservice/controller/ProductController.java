package com.patika.readerproductservice.controller;

import com.patika.readerproductservice.dto.request.ProductListSearchRequest;
import com.patika.readerproductservice.dto.request.ProductSaveRequest;
import com.patika.readerproductservice.dto.request.ProductSearchRequest;
import com.patika.readerproductservice.dto.response.GenericResponse;
import com.patika.readerproductservice.dto.response.ProductResponse;
import com.patika.readerproductservice.model.Product;
import com.patika.readerproductservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProductSaveRequest request) {
        log.info("Saving product {}", request);
        productService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public GenericResponse<Set<ProductResponse>> getAll(@RequestBody ProductSearchRequest request) {
        return GenericResponse.success(productService.getAll(request));
    }
    @GetMapping("/search")
    public GenericResponse<Set<ProductResponse>> getAllBySpesific(@RequestBody ProductSearchRequest request) {
        return GenericResponse.success(productService.search(request));
    }

    @GetMapping("/publishername/{publishername}")
    public GenericResponse<Set<ProductResponse>> getByPublisherName(@PathVariable("publishername") String publisherName) {
        return GenericResponse.success(productService.getAllByPublisherName(publisherName));
    }

    @GetMapping("/publisherid/{publisherid}")
    public GenericResponse<Set<ProductResponse>> getByPublisherId(@PathVariable("publisherid") String publisherId) {
        return GenericResponse.success(productService.getAllByPublisherName(publisherId));
    }

}
