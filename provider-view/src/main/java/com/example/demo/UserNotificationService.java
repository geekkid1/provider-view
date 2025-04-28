package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserNotificationService {
    public int notify(DummyNotificationClass notif) {
        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8082/notify";
        HttpEntity<DummyNotificationClass> request = new HttpEntity<>(notif);
        try {
            return rt.postForObject(url, request, Integer.class);
        } catch (Exception ignored) {
            return 1; // if there was an error, return the failure state!
        }
    }
}
