package com.formulate.formulatesb;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogoutRequest {
    public String sessionId;
}
