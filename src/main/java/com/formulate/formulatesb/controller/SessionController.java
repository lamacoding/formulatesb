package com.formulate.formulatesb.controller;

import com.formulate.formulatesb.LoginRequest;
import com.formulate.formulatesb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        sessionService.createSession(loginRequest);
        return new ResponseEntity<>(loginRequest.getUsername(), HttpStatus.OK);
    }
}
