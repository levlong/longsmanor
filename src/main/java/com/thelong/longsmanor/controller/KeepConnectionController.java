package com.thelong.longsmanor.controller;

import com.thelong.longsmanor.model.Ping;
import com.thelong.longsmanor.repository.PingRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepConnectionController {
    
    @Autowired
    private PingRepository repository;

    @GetMapping("/api/monitor")
    public List<Ping> monitor() {
        return repository.findAll();
    }
    
}
