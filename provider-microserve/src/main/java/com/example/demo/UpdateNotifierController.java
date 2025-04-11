package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class UpdateNotifierController {
    @Autowired UpdateNotifierService uns;

    @PostMapping("/updated/{id}")
    public ResponseEntity<?> updated(@PathVariable long id) {
        UpdateData n = new UpdateData();
        n.id = id;
        n.updated = true;
        n.t_stamp = Instant.now();
        uns.update(n);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Long>> all() {
        return ResponseEntity.status(HttpStatus.OK).body(uns.getUpdated());
    }
}
