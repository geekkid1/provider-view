package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DatabaseService {

    public FeedbackData[] getAll() {
        RestTemplate restTemplate = new RestTemplate();
        String allUrl = "http://localhost:8081/all";
        FeedbackData[] all = restTemplate.getForObject(allUrl, FeedbackData[].class);
        return all;
    }

    public FeedbackData[] getFbByProduct(String productName) {
        RestTemplate restTemplate = new RestTemplate();
        String allUrl = "http://localhost:8081/product?product=%s".formatted(productName);
        FeedbackData[] bp = restTemplate.getForObject(allUrl, FeedbackData[].class);
        return bp;
    }

    public void updateFilter(DummyFilterClass dfo) {
        RestTemplate restTemplate = new RestTemplate();
        String sendUrl = "http://localhost:8081/filter";
        HttpEntity<DummyFilterClass> request = new HttpEntity<>(dfo);
        DummyFilterClass dfr = restTemplate.postForObject(sendUrl, request, DummyFilterClass.class);
    }

    public DummyFilterClass getFilter(String product) {
        RestTemplate restTemplate = new RestTemplate();
        String getUrl = "http://localhost:8081/filter/" + product;
        DummyFilterClass dfr = restTemplate.getForObject(getUrl, DummyFilterClass.class);
        return dfr;
    }
}
