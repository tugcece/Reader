package com.patika.readerproductservice.converter;

import com.patika.readerproductservice.client.publisher.dto.response.PublisherResponse;
import com.patika.readerproductservice.dto.request.ProductSaveRequest;
import com.patika.readerproductservice.dto.response.ProductResponse;
import com.patika.readerproductservice.model.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static Product toProduct(ProductSaveRequest request, PublisherResponse publisherResponse) {
        return Product.builder()
                .name(request.getName())
                .amount(request.getAmount())
                .description(request.getDescription())
                .publisher(publisherResponse)
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .amount(product.getAmount())
                .name(product.getName())
                .description(product.getDescription())
                .publisherName(product.getPublisher().getName())
                .build();
    }

    public static Set<ProductResponse> toResponse(List<Product> products) {
        return products
                .stream()
                .map(ProductConverter::toResponse)
                .collect(Collectors.toSet());
    }
}
