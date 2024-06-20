package com.modakratelimitedchallenge;

import com.modakratelimitedchallenge.controllers.dto.request.SendNotificationRequest;
import com.modakratelimitedchallenge.entities.Notification;
import com.modakratelimitedchallenge.exceptions.LimitException;
import com.modakratelimitedchallenge.exceptions.TopicNotFound;
import com.modakratelimitedchallenge.repositories.NotificationRespository;
import com.modakratelimitedchallenge.services.NotificationService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
@TestPropertySource(properties = { "notifications-limits-maps.notificationLimitMap.Status.amount=2" })
@TestPropertySource(properties = { "notifications-limits-maps.notificationLimitMap.Status.days=0" })
@TestPropertySource(properties = { "notifications-limits-maps.notificationLimitMap.Status.hours=0" })
@TestPropertySource(properties = { "notifications-limits-maps.notificationLimitMap.Status.minutes=1" })
@TestPropertySource(properties = { "notifications-limits-maps.notificationLimitMap.Status.second=0" })
public class NotificationServiceTest {

    @Autowired
    @MockBean
    NotificationRespository notificationRespository;

    @Autowired
    NotificationService notificationService;

    @Test
    public void sendNotification_test_CREATED(){

        SendNotificationRequest sendNotificationRequest = SendNotificationRequest.builder()
                .content("content")
                .user("user1")
                .topic("Status")
                .build();

            UUID uuid = UUID.randomUUID();

        Notification notification = Notification.builder()
                .uuid(uuid)
                .user("user1")
                .topic("Status")
                .sendAt(LocalDateTime.now())
                .build();
        when(notificationRespository.findAllByUserAndTopicAndSendAtAfter(Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(List.of(notification));

        when(notificationRespository.save(Mockito.any())).thenReturn(notification);
        notificationService.send(sendNotificationRequest);
    }

    @Test(expected = TopicNotFound.class)
    public void sendNotification_Test_400_TopicNotFoundException(){

        SendNotificationRequest sendNotificationRequest = SendNotificationRequest.builder()
                .content("content")
                .user("user1")
                .topic("TOPIC")
                .build();

        notificationService.send(sendNotificationRequest);
    }

    @Test(expected = LimitException.class)
    public void sendNotification_Test_400_LimitException(){

        SendNotificationRequest sendNotificationRequest = SendNotificationRequest.builder()
                .content("content")
                .user("user2")
                .topic("Status")
                .build();

        UUID uuid = UUID.randomUUID();

        Notification notification = Notification.builder()
                .uuid(uuid)
                .user("user2")
                .topic("Status")
                .sendAt(LocalDateTime.now())
                .build();

        when(notificationRespository.findAllByUserAndTopicAndSendAtAfter(Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(List.of(notification,notification));


        notificationService.send(sendNotificationRequest);
    }
}
