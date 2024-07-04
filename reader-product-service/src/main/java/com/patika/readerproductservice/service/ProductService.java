package com.patika.readerproductservice.service;

import com.patika.readerproductservice.client.publisher.dto.response.PublisherResponse;
import com.patika.readerproductservice.client.publisher.service.PublisherService;
import com.patika.readerproductservice.converter.ProductConverter;
import com.patika.readerproductservice.dto.request.ProductListSearchRequest;
import com.patika.readerproductservice.dto.request.ProductSaveRequest;
import com.patika.readerproductservice.dto.request.ProductSearchRequest;
import com.patika.readerproductservice.dto.response.ProductResponse;
import com.patika.readerproductservice.model.Product;
import com.patika.readerproductservice.repository.ProductRepository;
import com.patika.readerproductservice.repository.spesification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final PublisherService publisherService;

    public void save(ProductSaveRequest request) {

      Optional<PublisherResponse> optionalPublisher = publisherService.getPublisherByName(request.getPublisherName());

      if (optionalPublisher.isEmpty()) {
            log.error("publisher bulamadım : {}", request.getPublisherName());
            throw new RuntimeException("publisher bulamadım");
        }

    Product product = ProductConverter.toProduct(request, optionalPublisher.get());

        productRepository.save(product);

        log.info("product created : {}", product.toString());
    }

    public Set<ProductResponse> getAll(ProductSearchRequest request) {

        Specification<Product> productSpecification = ProductSpecification.initProductSpecification(request);

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.ASC, "amount"));

        Page<Product> products = productRepository.findAll(productSpecification, pageRequest);

        log.info("db'den getirildi. product size:{}", products.getSize());

        return ProductConverter.toResponse(products.stream().toList());
    }

    public Set<ProductResponse> search(ProductSearchRequest request) {
        Specification<Product> productSpecification = ProductSpecification.initProductSpecification(request);

        Sort sort = Sort.by(Sort.Direction.ASC, "amount");
        if (request.getSortBy() != null && request.getSortDirection() != null) {
            if (request.getSortDirection().equals("ASC")){
                sort = Sort.by(Sort.Direction.ASC, request.getSortBy());
            }else{
                sort = Sort.by(Sort.Direction.DESC, request.getSortBy());
            }
        }
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), sort);

        Page<Product> products = productRepository.findAll(productSpecification, pageRequest);

        return ProductConverter.toResponse(products.stream().toList());
    }



    public Set<ProductResponse> getAllByPublisherName(String publisherName) {
       return ProductConverter.toResponse(productRepository.getAllByPublisherName(publisherName));
    }

    public Set<ProductResponse> getAllByPublisherId(Long publisherId) {
        return ProductConverter.toResponse(productRepository.getAllByPublisherId(publisherId));
    }

    public Set<ProductResponse> getByIdList() {
        List<Product> products = productRepository.findAll().stream().toList();
        return ProductConverter.toResponse(products);
    }
}
