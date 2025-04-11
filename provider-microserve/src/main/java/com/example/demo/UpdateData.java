package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class UpdateData {
    @Id
    long id;
    boolean updated;
    Instant t_stamp;
}
