package com.patika.readerproductservice.service;


import com.patika.readerproductservice.dto.request.ProductSearchRequest;
import com.patika.readerproductservice.dto.response.ProductResponse;
import com.patika.readerproductservice.model.Product;
import com.patika.readerproductservice.repository.ProductRepository;
import com.patika.readerproductservice.converter.ProductConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductConverter productConverter;

    @Test
    void testSearch() {
        // given

        Product product = new Product();
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product));
        when(productRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(productPage);

        ProductResponse productResponse = new ProductResponse();
        Set<ProductResponse> productResponseSet = Set.of(productResponse);
        when(productConverter.toResponse(anyList())).thenReturn(productResponseSet);

        // when
        Set<ProductResponse> result = productService.search(buildProductSearchRequest());

        // then
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll(any(Specification.class), any(PageRequest.class));
        verify(productConverter, times(1)).toResponse(anyList());
    }
    public ProductSearchRequest buildProductSearchRequest() {
        ProductSearchRequest request = new ProductSearchRequest();
        request.setName("test");
        request.setAmount(BigDecimal.valueOf(100.0));
        request.setPublisherName("testPublisher");
        request.setProductIds(List.of(1L, 2L));
        request.setPage(0);
        request.setSize(10);
        request.setSortBy("amount");
        request.setSortDirection("ASC");

        return request;
    }
}
