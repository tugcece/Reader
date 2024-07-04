package com.patika.readernotificationservice.strategy;

import com.patika.readernotificationservice.consumer.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PushNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(NotificationDto notificationDto) {
        log.info("Push Notification Sent: {}", notificationDto.toString());
    }
}
