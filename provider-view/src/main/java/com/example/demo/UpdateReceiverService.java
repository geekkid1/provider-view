package com.example.demo;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UpdateReceiverService {
    public List<Long> getUpdated() {
        RestTemplate restTemplate = new RestTemplate();
        String upUrl = "http://localhost:8080/all"; // 8080 is provider/microservice, 8081 is database
        Long[] updates = restTemplate.getForObject(upUrl, Long[].class);
        return Arrays.asList(updates);
    }
}
