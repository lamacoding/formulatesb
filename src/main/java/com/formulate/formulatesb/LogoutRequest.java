package com.formulate.formulatesb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class LogoutRequest {
    public String sessionId;
    public String username; //eq email address
}
