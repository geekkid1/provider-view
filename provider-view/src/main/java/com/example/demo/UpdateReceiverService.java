package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UpdateReceiverService {
    public List<Long> getUpdated() {
        RestTemplate restTemplate = new RestTemplate();
        String upUrl = "http://localhost:8082/all"; // 8082 is microservice
        try {
            ResponseEntity<Long[]> updates = restTemplate.getForEntity(upUrl, Long[].class);
            if (updates.getStatusCode() == HttpStatus.OK && updates.hasBody()) {
                return new ArrayList<>(Arrays.asList(updates.getBody()));
            } else {
                return new ArrayList<>(); // else return empty list?
            }
        } catch (Exception ignored) {
            return new ArrayList<>();
        }

    }
}
