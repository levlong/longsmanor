package com.thelong.longsmanor.dto;

import java.time.LocalDateTime;

public class PingResponse {
    private String status;
    private LocalDateTime ping_time;
    private String log;

    public PingResponse() {

    }

    public PingResponse(String status, LocalDateTime ping_time, String log) {
        this.status = status;
        this.ping_time = ping_time;
        this.log = log;
    }

    public String getStatus() {
        return this.status;
    }

    public LocalDateTime getPingTime() {
        return this.ping_time;
    }

    public String getLog() {
        return this.log;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPingTime(LocalDateTime ping_time) {
        this.ping_time = ping_time;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
