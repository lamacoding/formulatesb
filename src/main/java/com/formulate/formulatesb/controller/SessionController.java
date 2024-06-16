package com.formulate.formulatesb.controller;

import com.formulate.formulatesb.LoginRequest;
import com.formulate.formulatesb.LogoutRequest;
import com.formulate.formulatesb.model.Session;
import com.formulate.formulatesb.model.User;
import com.formulate.formulatesb.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class SessionController {
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
    @Autowired
    private SessionService sessionService;

    @PostMapping("/login")
    public ResponseEntity<Session> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login request: " + loginRequest.getUsername());
        Session createdSession = sessionService.createSession(loginRequest);
        return new ResponseEntity<>(createdSession, createdSession == null ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest logoutRequest) {
        logger.info("LogoutRequest: sessionId " + logoutRequest.getSessionId());
        try {
            sessionService.destroySession(logoutRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<Boolean> getSessionValidity(@PathVariable String sessionId) {
        logger.info("getSessionValidity: " + sessionId);
        if(sessionId == null || sessionId.isEmpty()) {
            logger.error("sessionId is null or empty");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sessionService.getSessionValidity(sessionId), HttpStatus.OK);
    }

    @GetMapping("/session/userbyid")
    public ResponseEntity<User> getUserBySessionId(@RequestParam("sessionId") String sessionId) {
        User user = sessionService.getUserBySessionId(sessionId);
        return new ResponseEntity<>(user, user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
