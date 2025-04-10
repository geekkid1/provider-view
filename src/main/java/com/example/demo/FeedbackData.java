package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class FeedbackData {
    long id;
    @JsonProperty("product_name")
    String productName;
    String content;
    @JsonProperty("metadata")
    Map<String,String> metaData;

    public FeedbackData() {}
    public FeedbackData(long id, String pn, String c, Map<String,String> md) {
        this.id = id;
        productName = pn;
        content = c;
        metaData = md;

    }
    public long getId() {
        return id;
    }
    public String getProductName() {
        return productName;
    }
    public String getContent() {
        return content;
    }
    public Map<String,String> getMetaData() {
        return metaData;
    }
}
