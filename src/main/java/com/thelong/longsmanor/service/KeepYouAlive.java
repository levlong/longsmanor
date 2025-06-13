package com.thelong.longsmanor.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeepYouAlive {

    private static final String URL = "https://webhook-hi7l.onrender.com/wake-up";
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 600000)
    public void awake() {
        try {
            String response = restTemplate.getForObject(URL, String.class);
            System.out.println("Awake " + java.time.LocalDateTime.now() + ". Extra info: " + response);
        } catch (Exception e) {
            System.err.println("He's still sleeping " + java.time.LocalDateTime.now() + ": " + e.getMessage());
        }
    }
}
