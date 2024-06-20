package com.modakratelimitedchallenge.repositories;

import com.modakratelimitedchallenge.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRespository extends JpaRepository<Notification, String> {

    @Query("SELECT n FROM Notification n WHERE n.user = :user AND n.topic = :topic AND n.sendAt >= :searchTime")
    List<Notification> findAllByUserAndTopicAndSendAtAfter(String user, String topic, LocalDateTime searchTime);
}
