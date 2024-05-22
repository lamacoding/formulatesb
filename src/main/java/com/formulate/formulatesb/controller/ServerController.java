package com.formulate.formulatesb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Server running...", HttpStatus.OK);
    }
}
