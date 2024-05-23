package com.formulate.formulatesb.controller;

import com.formulate.formulatesb.LoginRequest;
import com.formulate.formulatesb.model.Session;
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
    public ResponseEntity<Session> login(@RequestBody LoginRequest loginRequest) {
        Session createdSession = sessionService.createSession(loginRequest);
        System.out.println("Login request: " + loginRequest);
        System.out.println(createdSession == null ? "failed" : createdSession.getSessionId());
        return new ResponseEntity<>(createdSession, createdSession == null ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
    }

    @GetMapping("/logout/{sessionId}")
    public ResponseEntity<Void> logout(@RequestBody String sessionId) {
        try {
            sessionService.destroySession(sessionId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<Boolean> getSessionValidity(@PathVariable String sessionId) {
        return new ResponseEntity<>(sessionService.getSessionValidity(sessionId), HttpStatus.OK);
    }
}
