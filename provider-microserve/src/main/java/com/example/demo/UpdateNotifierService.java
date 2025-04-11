package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateNotifierService {
    @Autowired UpdateRepository repo;

    public void update(UpdateData ud) {
        // time stamp equivalent to five minutes ago
        Instant fma = Instant.now().minus(5, ChronoUnit.MINUTES);
        if(ud.t_stamp.isAfter(fma)) {
            repo.save(ud);
        } else {
            // still save the last update
            ud.updated = false;
            repo.save(ud);
        }
    }

    public ArrayList<Long> getUpdated() {
        ArrayList<Long> temp = new ArrayList<>();
        // time stamp equivalent to five minutes ago
        Instant fma = Instant.now().minus(5, ChronoUnit.MINUTES);
        List<UpdateData> outdated = repo.getByUpdated(true);
        for(UpdateData ud : outdated) {
            if(ud.t_stamp.isAfter(fma)) {
                temp.add(ud.id);
            } else {
                ud.updated = false;
                repo.save(ud); // overwrite it with false so it isn't picked up next time
            }
        }
        return temp;
    }
}
