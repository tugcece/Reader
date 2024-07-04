package com.patika.readernotificationservice.service;

import com.patika.readernotificationservice.consumer.dto.NotificationDto;
import com.patika.readernotificationservice.consumer.dto.enums.NotificationStatus;
import com.patika.readernotificationservice.consumer.dto.enums.NotificationType;
import com.patika.readernotificationservice.converter.NotificationConverter;
import com.patika.readernotificationservice.model.Notification;
import com.patika.readernotificationservice.repository.NotificationRepository;
import com.patika.readernotificationservice.strategy.EmailNotificationStrategy;
import com.patika.readernotificationservice.strategy.NotificationStrategy;
import com.patika.readernotificationservice.strategy.PushNotificationStrategy;
import com.patika.readernotificationservice.strategy.SmsNotificationStrategy;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {
    private Map<NotificationType, NotificationStrategy> strategies = new HashMap<>();
    private final NotificationRepository notificationRepository;

    public NotificationService(EmailNotificationStrategy emailNotificationStrategy,
                               SmsNotificationStrategy smsNotificationStrategy,
                               PushNotificationStrategy pushNotificationStrategy, NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        strategies.put(NotificationType.MAIL, emailNotificationStrategy);
        strategies.put(NotificationType.SMS, smsNotificationStrategy);
        strategies.put(NotificationType.PUSH_NOTIFICATION, pushNotificationStrategy);
    }

    public void sendNotification(NotificationDto notificationDto) {
        NotificationStrategy strategy = strategies.get(notificationDto.getNotificationType());
        if (strategy != null) {
            strategy.sendNotification(notificationDto);
        } else {
            throw new IllegalArgumentException("Invalid notification type: " + notificationDto.getNotificationType());
        }
        saveNotification(notificationDto);
    }

    public void saveNotification(NotificationDto notificationDto) {
        String content = "notification";
        Notification notification = NotificationConverter.toNotification(notificationDto, content);
        notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
       return notificationRepository.findAll().stream().toList();
    }


    public List<Notification> getByStatusFailed() {
        return notificationRepository.findByNotificationStatus(NotificationStatus.FAILED);    }
}
