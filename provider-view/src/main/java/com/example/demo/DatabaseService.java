package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DatabaseService {
    public FeedbackData[] getFbByProduct(String productName) {
        RestTemplate restTemplate = new RestTemplate();
        String allUrl = "http://localhost:8081/product?product=%s".formatted(productName);
        FeedbackData[] bp = restTemplate.getForObject(allUrl, FeedbackData[].class);
        return bp;
    }
}
