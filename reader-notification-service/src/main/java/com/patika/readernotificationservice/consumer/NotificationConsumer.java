package com.patika.readernotificationservice.consumer;

import com.patika.readernotificationservice.consumer.dto.NotificationDto;
import com.patika.readernotificationservice.consumer.dto.enums.NotificationStatus;
import com.patika.readernotificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${notification.queue}")
    public void receiveNotification(NotificationDto notificationDto) {
        try {
            log.info("Received notification: {}", notificationDto.toString());
            notificationService.sendNotification(notificationDto);
            notificationDto.setNotificationStatus(NotificationStatus.SENT);
        } catch (Exception e) {
            notificationDto.setNotificationStatus(NotificationStatus.FAILED);
            log.error("Notification processing failed", e);
        }

    }
}
