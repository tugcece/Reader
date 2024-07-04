package com.patika.readernotificationservice.dto.request;

import com.patika.readernotificationservice.consumer.dto.enums.NotificationStatus;
import com.patika.readernotificationservice.consumer.dto.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSaveRequest {

    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private String content;
}
