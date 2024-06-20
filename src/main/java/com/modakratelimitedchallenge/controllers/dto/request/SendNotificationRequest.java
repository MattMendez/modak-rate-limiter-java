package com.modakratelimitedchallenge.controllers.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Request for creating a notification.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SendNotificationRequest", description = "Request used to send a new notification to an user")
public class SendNotificationRequest {

    @NotNull(message = "The field user is required")
    @NotEmpty
    @Schema(name = "user", description = "user to send the notification", example = "user123")
    private String user;

    @NotNull(message = "The field topic is required")
    @NotEmpty
    @Schema(name = "content", description = "The content of the email", example = "{Your content here}")
    private String content;

    @Email
    @NotNull(message = "The field topic is required")
    @NotEmpty
    @Schema(name = "topic", description = "Topic of the notification", example = "news")
    private String topic;
}
