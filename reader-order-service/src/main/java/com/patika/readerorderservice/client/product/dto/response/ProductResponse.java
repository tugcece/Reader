package com.patika.readerorderservice.client.product.dto.response;

import com.patika.readerorderservice.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
   private Long productId;
   private String name;
   private String description;
}
