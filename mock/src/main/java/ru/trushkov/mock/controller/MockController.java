package ru.trushkov.mock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class MockController {

    @GetMapping("/200")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok().body("200");
    }

    @GetMapping("/500")
    public ResponseEntity<String> error() {
        return ResponseEntity.internalServerError().build();
    }

}
