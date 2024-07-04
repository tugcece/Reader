package com.patika.readernotificationservice.consumer.dto;

import com.patika.readernotificationservice.consumer.dto.enums.NotificationStatus;
import com.patika.readernotificationservice.consumer.dto.enums.NotificationType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NotificationDto {
    private Long id;
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private List<ProductDto> productDtoList;
    private BigDecimal totalAmount;
    private String customerName;
    private String customerEmail;
    private String customerLastName;

}
