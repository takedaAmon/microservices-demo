package com.example.servicec.controller; // <--- Важно!

import com.example.servicec.service.FinalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api") // <--- Если этого нет, адрес будет просто /save
@RequiredArgsConstructor
public class FinalController {

    private final FinalService service;

    @PostMapping("/save") // <--- Если этого нет, будет 404
    public String saveData(@RequestBody Map<String, String> request) {
        String msg = request.get("message");
        service.finalizeData(msg);
        return "Saved and Published";
    }
}