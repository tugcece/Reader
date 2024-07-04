package com.patika.readernotificationservice.controller;

import com.patika.readernotificationservice.consumer.dto.NotificationDto;
import com.patika.readernotificationservice.dto.request.NotificationSearchRequest;
import com.patika.readernotificationservice.dto.response.GenericResponse;
import com.patika.readernotificationservice.model.Notification;
import com.patika.readernotificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void save(@RequestBody NotificationDto notificationDto) {
        notificationService.saveNotification(notificationDto);
    }

    @GetMapping
    public List<Notification> getAll() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/failed")
    public GenericResponse<List<Notification>> getByStatusFailed() {
        List<Notification> notification = notificationService.getByStatusFailed();
        return GenericResponse.success(notification);
    }

}
