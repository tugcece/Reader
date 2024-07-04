package com.patika.readerproductservice.repository.spesification;

import com.patika.readerproductservice.client.publisher.dto.response.PublisherResponse;
import com.patika.readerproductservice.dto.request.ProductSearchRequest;
import com.patika.readerproductservice.model.Product;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSpecification {

    public static Specification<Product> initProductSpecification(ProductSearchRequest request) {
        return (root, query, criteriaBuilder) -> {

            Join<Product, PublisherResponse> publisherJoin = root.join("publisher");

            List<Predicate> predicateList = new ArrayList<>();

            if (request.getName() != null) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%"));
            }

            if (request.getAmount() != null) {
                predicateList.add(criteriaBuilder.greaterThan(root.get("amount"), request.getAmount()));
            }

            if (request.getPublisherName() != null) {
                predicateList.add(criteriaBuilder.like(publisherJoin.get("name"), "%" + request.getPublisherName() + "%"));
            }

            if (request.getProductIds() != null && !request.getProductIds().isEmpty()) {
                predicateList.add(root.get("id").in(request.getProductIds()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
