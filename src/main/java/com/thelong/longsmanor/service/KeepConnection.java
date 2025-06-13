package com.thelong.longsmanor.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepConnection {

    private static final String URL = "https://webhook-hi7l.onrender.com/wake-up";
    private final RestTemplate restTemplate = new RestTemplate();
    private final List<String> logs = Collections.synchronizedList(new LinkedList<>());

    @Scheduled(fixedRate = 600000)
    public void awake() {
        try {
            String response = restTemplate.getForObject(URL, String.class);
            addLogs("✅SUCCESS at " + java.time.LocalDateTime.now() + " - " +"Response from server: " + response);
        } catch (Exception e) {
            addLogs("❌FAIL at " + java.time.LocalDateTime.now() + ": " + e.getMessage());
        }
    }

    public void addLogs(String msg) {
        if (logs.size() > 100) {
            logs.remove(0);
        }

        logs.add(msg);
    }

    public List<String> getLogs() {
        return logs;
    }
}
