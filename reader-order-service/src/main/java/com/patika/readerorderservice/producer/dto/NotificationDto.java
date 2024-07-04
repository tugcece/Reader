package com.patika.readerorderservice.producer.dto;

import com.patika.readerorderservice.producer.dto.enums.NotificationType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationDto {

    private NotificationType notificationType;
    private List<ProductDto> productDtoList;
    private BigDecimal totalAmount;

}
