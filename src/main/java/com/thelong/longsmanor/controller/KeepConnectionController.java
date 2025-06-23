package com.thelong.longsmanor.controller;

import com.thelong.longsmanor.dto.PingResponse;
import com.thelong.longsmanor.service.KeepConnection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepConnectionController {
    
    @Autowired
    private KeepConnection service;

    @GetMapping("/api/monitor")
    public List<PingResponse> monitor() {
        return service.getLogs();
    }
    
}
