package com.thelong.longsmanor.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thelong.longsmanor.dto.PingResponse;

@Component
public class KeepConnection {

    private static final String URL = "https://webhook-hi7l.onrender.com/wake-up";
    private final RestTemplate restTemplate = new RestTemplate();
    private final List<PingResponse> logs = Collections.synchronizedList(new LinkedList<>());

    @Scheduled(fixedRate = 600000)
    public void awake() {
        try {
            String response = restTemplate.getForObject(URL, String.class);
            if (response.isEmpty()) {
                addLogs(new PingResponse("❌Fail", LocalDateTime.now(), response));
            }
            addLogs(new PingResponse("✅Success", LocalDateTime.now(), response));

        } catch (Exception e) {
            
        }
    }

    public void addLogs(PingResponse response) {
        if (logs.size() > 100) {
            logs.remove(0);
        }

        logs.add(response);
    }

    public List<PingResponse> getLogs() {
        return logs;
    }
}
