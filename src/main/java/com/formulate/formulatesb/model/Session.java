package com.formulate.formulatesb.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "sessions")
public class Session {
    @Id
    private String sessionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @NotNull
    private String userId;
}
