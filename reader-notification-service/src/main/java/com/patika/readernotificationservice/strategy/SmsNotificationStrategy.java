package com.patika.readernotificationservice.strategy;

import com.patika.readernotificationservice.consumer.dto.NotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(NotificationDto notificationDto) {
        log.info("SMS Notification Sent: {}", notificationDto.toString());
    }
}
