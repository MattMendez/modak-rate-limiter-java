package com.modakratelimitedchallenge.configurations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@NoArgsConstructor
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "notifications-limits-maps")
public class NotificationsLimitConfiguration {

    private Map<String, Limits> notificationLimitMap;

    public boolean verifyTopic(String topic) {
        return notificationLimitMap.containsKey(topic);
    }

    public Limits getLimits(String topic) {
        return notificationLimitMap.get(topic);
    }

    /**
     * Pojo class of the times between notifications.
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Limits {
        private Integer amount;
        private Long days;
        private Long hours;
        private Long minutes;
        private Long second;
    }
}
