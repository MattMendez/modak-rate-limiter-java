package com.modakratelimitedchallenge.services.impl;

import com.modakratelimitedchallenge.configurations.NotificationsLimitConfiguration;
import com.modakratelimitedchallenge.controllers.dto.request.SendNotificationRequest;
import com.modakratelimitedchallenge.entities.Notification;
import com.modakratelimitedchallenge.exceptions.ErrorSendingException;
import com.modakratelimitedchallenge.exceptions.LimitException;
import com.modakratelimitedchallenge.exceptions.SqlException;
import com.modakratelimitedchallenge.exceptions.TopicNotFound;
import com.modakratelimitedchallenge.repositories.NotificationRespository;
import com.modakratelimitedchallenge.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRespository notificationRepository;
    private final NotificationsLimitConfiguration notificationsLimitConfiguration;
    private final Gateway gateway;

    @Override
    public void send(SendNotificationRequest request) {
        log.info("[send] Star sending notifiaction to user {} with topic {}",request.getUser(),request.getTopic());

        if(!notificationsLimitConfiguration.verifyTopic(request.getTopic())){
            log.error("Topic {} is not supported", request.getTopic());
            throw new TopicNotFound();
        }

        if (isRateLimitExceeded(request.getUser(), request.getTopic())) {
            log.info("Rate limit exceeded for user {} with topic {}",request.getUser(),request.getTopic());
            throw new LimitException();
        }

        Notification notification = Notification.builder()
                .user(request.getUser())
                .topic(request.getTopic())
                .build();

        try {
            notificationRepository.save(notification);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new SqlException();
        }

        try {
            gateway.send(request.getUser(), request.getContent());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErrorSendingException();
        }
        log.info("[send] Finish sending notifiaction to user {} with topic {}",request.getUser(),request.getTopic());

    }

    private boolean isRateLimitExceeded(String user, String topic) {
        NotificationsLimitConfiguration.Limits limits = notificationsLimitConfiguration.getLimits(topic);

        LocalDateTime searhTime = LocalDateTime.now()
                .minusDays(limits.getDays())
                .minusHours(limits.getHours())
                .minusMinutes(limits.getMinutes())
                .minusSeconds(limits.getSecond());

        List<Notification> notificationList = notificationRepository.findAllByUserAndTopicAndSendAtAfter(user,topic,searhTime);
        return notificationList.size() >= limits.getAmount();
    }

    @Override
    public List<Notification> findAllNotifications(){
        return notificationRepository.findAll();
    }

}
