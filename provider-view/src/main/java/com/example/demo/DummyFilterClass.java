package com.example.demo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DummyFilterClass {
    @JsonProperty("len")
    public char len;
    @JsonProperty("product")
    public String product;
    //public Map<String, Object> uiModel;


    public DummyFilterClass(char len, String product) {
        this.len = len;
        this.product = product;
    }
    public DummyFilterClass(String product) {
        this.product = product;
    }
    /*public DummyFilterClass(Map<String, Object> model) {
        uiModel = model;
    }*/
    public DummyFilterClass() {}
}
