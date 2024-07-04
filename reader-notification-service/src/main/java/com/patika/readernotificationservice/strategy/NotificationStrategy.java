package com.patika.readernotificationservice.strategy;

import com.patika.readernotificationservice.consumer.dto.NotificationDto;

public interface NotificationStrategy {
    void sendNotification(NotificationDto notificationDto);
}
