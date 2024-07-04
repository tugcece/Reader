package com.patika.readerorderservice.model.spesification;

import com.patika.readerorderservice.dto.request.OrderSearchRequest;
import com.patika.readerorderservice.model.Order;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderSpecification {

    public static Specification<Order> initProductSpecification(OrderSearchRequest request) {
        return (root, query, criteriaBuilder) -> {


            List<Predicate> predicateList = new ArrayList<>();

            if (request.getOrderCode() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("order_code"), request.getOrderCode()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
