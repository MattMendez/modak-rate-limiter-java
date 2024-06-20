package com.modakratelimitedchallenge.controllers;

import com.modakratelimitedchallenge.controllers.dto.request.SendNotificationRequest;

import com.modakratelimitedchallenge.entities.Notification;
import com.modakratelimitedchallenge.services.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @PostMapping("/send")
    @Operation(summary="Send a notification to a user",
            description ="Service to send a user a notification based on the last notifications")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request format",
            required = true,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = @ExampleObject("{\n" +
                            "    \"user\":\"user1\",\n" +
                            "    \"content\": \"content\",\n" +
                            "    \"topic\":\"Status\"\n" +
                            "}")
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Sent successfully",
                                    value = "")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Different errors",
                                    value =  "")
                    )
            )
    })
    public ResponseEntity<Void> sendNewNotification(@RequestBody SendNotificationRequest request){
         notificationService.send(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/send")
    public ResponseEntity<List<Notification>> findAllNotifications(){
        return ResponseEntity.ok(notificationService.findAllNotifications());
    }
}
