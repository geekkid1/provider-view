package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UpdateRepository extends CrudRepository<UpdateData, Long> {
    List<UpdateData> getByUpdated(boolean updated);
}
