package com.patika.readernotificationservice.converter;

import com.patika.readernotificationservice.consumer.dto.NotificationDto;
import com.patika.readernotificationservice.consumer.dto.enums.NotificationStatus;
import com.patika.readernotificationservice.model.Notification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationConverter {

    public static Notification toNotification(NotificationDto notificationDto, String content) {
        return Notification.builder()
                .id(notificationDto.getId())
                .notificationType(notificationDto.getNotificationType())
                .notificationStatus(NotificationStatus.FAILED)
                .content(content)
                .build();
    }
    public static Notification toResponse(Notification notification) {
        return Notification.builder()
                .content(notification.getContent())
                .notificationType(notification.getNotificationType())
                .notificationStatus(notification.getNotificationStatus())
                .id(notification.getId())
                .build();
    }

    public static Set<Notification> toResponse(List<Notification> notifications) {
        return notifications
                .stream()
                .map(NotificationConverter::toResponse)
                .collect(Collectors.toSet());
    }

}

