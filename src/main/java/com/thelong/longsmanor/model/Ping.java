package com.thelong.longsmanor.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ping {
    @Id
    private String id;
    private String status;
    private String logMessage;
    private LocalDateTime timePinged;
}
