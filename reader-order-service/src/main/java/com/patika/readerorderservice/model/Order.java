package com.patika.readerorderservice.model;

import com.patika.readerorderservice.client.customer.dto.response.CustomerResponse;
import com.patika.readerorderservice.client.product.dto.response.ProductResponse;
import com.patika.readerorderservice.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "create_datetime")
    private LocalDateTime createDate;

    @ElementCollection
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "product_ids")
    private List<Long> productIds;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerResponse customerId;


}
