package com.formulate.formulatesb.service;

import com.formulate.formulatesb.LoginRequest;
import com.formulate.formulatesb.LogoutRequest;
import com.formulate.formulatesb.model.User;
import com.formulate.formulatesb.model.Session;
import com.formulate.formulatesb.repository.SessionRepository;
import com.formulate.formulatesb.util.PasswordService;
import com.formulate.formulatesb.util.RandomHashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        requestedSession.setUserId(userService.getUserByEmail(loginRequest.getUsername()).getId().toHexString());
        requestedSession.setStartTime(LocalDateTime.now());
        requestedSession.setEndTime(LocalDateTime.now().plusMinutes(10));
        return sessionRepository.save(requestedSession);
    }

    public Boolean getSessionValidity(String sessionId) {
        Optional<Session> currentSession = sessionRepository.findById(sessionId);

        if (currentSession.isPresent()) {
            if(!currentSession.get().getEndTime().isBefore(LocalDateTime.now())) {
                currentSession.get().setEndTime(LocalDateTime.now().plusMinutes(10));
                sessionRepository.save(currentSession.get());
                return true;
            }
        }
        return false;
    }

    public void destroySession(LogoutRequest logoutRequest) {
        Optional<Session> currentSession = sessionRepository.findById(logoutRequest.getSessionId());
        if (currentSession.isPresent()) {
            Session session = currentSession.get();
            sessionRepository.delete(session);
        }
    }

    public User getUserBySessionId(String sessionId) {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        if (sessionOptional.isPresent()) {
            return userService.getUserById(sessionOptional.get().getUserId());
        } else {
            return null;
        }
    }

    private Boolean verifyCredentials(LoginRequest loginRequest) {
        User user = userService.getUserByEmail(loginRequest.getUsername());
        return user != null && user.getPassword().equals(PasswordService.hashPassword(loginRequest.getPassword()));
    }
}
