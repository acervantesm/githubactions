package com.apress.users.web;

import com.apress.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@RestController
@RequestMapping("/external")
public class ExternalAPIController {
    private final RestTemplate restTemplate = new RestTemplate();
    @GetMapping
    public ResponseEntity<String> getAll() {
        System.out.println("External =====================");
        return restTemplate.getForEntity("http://localhost:8081/users", String.class);
    }
}
