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
    @JsonProperty("bp")
    public char bp;


    public DummyFilterClass(char len, String product) {
        this.len = len;
        this.product = product;
    }
    public DummyFilterClass(String product) {
        this.product = product;
    }
    public DummyFilterClass() {}
}
