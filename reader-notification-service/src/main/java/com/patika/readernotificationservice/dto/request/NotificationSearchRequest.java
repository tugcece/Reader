package com.patika.readernotificationservice.dto.request;

import com.patika.readernotificationservice.consumer.dto.enums.NotificationStatus;
import com.patika.readernotificationservice.consumer.dto.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSearchRequest extends BaseSearchRequest {
    private Long id;
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private String content;
}
