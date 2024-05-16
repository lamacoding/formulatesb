package com.formulate.formulatesb.service;

import com.formulate.formulatesb.LoginRequest;
import com.formulate.formulatesb.model.User;
import com.formulate.formulatesb.model.Session;
import com.formulate.formulatesb.repository.SessionRepository;
import com.formulate.formulatesb.util.PasswordService;
import com.formulate.formulatesb.util.RandomHashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserService userService;

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session createSession(LoginRequest loginRequest) {
        if (!verifyCredentials(loginRequest)) {
            return null;
        }
        Session requestedSession = new Session();
        requestedSession.setSessionId(RandomHashGenerator.generateRandomHash());
        requestedSession.setUser(userService.getUserByEmail(loginRequest.getUsername()));
        requestedSession.setStartTime(LocalDateTime.now());
        requestedSession.setEndTime(LocalDateTime.now().plusMinutes(10));
        return sessionRepository.save(requestedSession);
    }

    private Boolean verifyCredentials(LoginRequest loginRequest) {
        User user = userService.getUserByEmail(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(PasswordService.hashPassword(loginRequest.getPassword()))) {
            return true;
        }
        return false;
    }
}
