package com.patika.readernotificationservice.repository;

import com.patika.readernotificationservice.consumer.dto.enums.NotificationStatus;
import com.patika.readernotificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>, JpaSpecificationExecutor<Notification> {
    List<Notification> findByNotificationStatus(NotificationStatus notificationStatus);
}
