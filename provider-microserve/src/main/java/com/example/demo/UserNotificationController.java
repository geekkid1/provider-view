package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserNotificationController {
    @PostMapping("/notify")
    public int notify(@RequestBody NotificationData nd) {
        return 1; // default failure state since API does not exist yet.
    }
}
