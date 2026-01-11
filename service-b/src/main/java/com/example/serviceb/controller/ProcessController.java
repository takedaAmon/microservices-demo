package com.example.serviceb.controller;

import com.example.serviceb.dto.MessageDto;
import com.example.serviceb.service.ProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessingService processingService;

    @PostMapping("/process")
    public String processMessage(@RequestBody MessageDto dto) {
        processingService.process(dto);
        return "Processed successfully";
    }
}