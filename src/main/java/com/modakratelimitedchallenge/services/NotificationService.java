package com.modakratelimitedchallenge.services;

import com.modakratelimitedchallenge.controllers.dto.request.SendNotificationRequest;
import com.modakratelimitedchallenge.entities.Notification;

import java.util.List;

public interface NotificationService {

    void send(SendNotificationRequest request);

    List<Notification> findAllNotifications();
}
