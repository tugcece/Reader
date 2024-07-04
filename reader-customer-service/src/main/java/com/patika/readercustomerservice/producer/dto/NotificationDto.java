package com.patika.readercustomerservice.producer.dto;

import com.patika.readercustomerservice.model.Customer;
import com.patika.readercustomerservice.producer.dto.enums.NotificationType;
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
    private String customerName;
    private String customerEmail;
    private String customerLastName;

}
