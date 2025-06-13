package com.thelong.longsmanor.controller.KeepConnection;

import com.thelong.longsmanor.service.KeepConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class KeepConnectionController {
    
    @Autowired
    private KeepConnection service;

    @GetMapping("/monitor")
    public String monitor(Model model) {
        model.addAttribute("logs", service.getLogs());

        return "monitor";
    }
    
}
