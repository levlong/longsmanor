package com.thelong.longsmanor.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thelong.longsmanor.model.Ping;
import com.thelong.longsmanor.repository.PingRepository;

@Component
public class KeepConnection {

    private static final String URL = "https://webhook-5at4.onrender.com/wake-up";
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private PingRepository repository;

    @Scheduled(fixedRate = 600000)
    public void awake() {
        Ping pingResponse = new Ping();
        LocalDateTime now = LocalDateTime.now();
        try {
            String response = restTemplate.getForObject(URL, String.class);
            if (response.isEmpty()) {
                pingResponse.setStatus("Error");
                pingResponse.setLogMessage("Oops! Something went wrong" + response);
            } else {
                pingResponse.setStatus("Success");
                pingResponse.setLogMessage(response);
            }

            pingResponse.setTimePinged(now);
            repository.save(
                Ping.builder()
                .status(pingResponse.getStatus())
                .logMessage(pingResponse.getLogMessage())
                .timePinged(pingResponse.getTimePinged())
                .build()
            );

        } catch (Exception e) {
            pingResponse.setStatus("Error");
            pingResponse.setLogMessage("Exception occurred: " + e.getMessage());
        }
    }
}
