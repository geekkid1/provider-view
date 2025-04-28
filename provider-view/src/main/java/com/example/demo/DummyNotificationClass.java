package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DummyNotificationClass {
    public String product;
    public long uid;

    public DummyNotificationClass(String product) {
        this.product = product;
    }
}
