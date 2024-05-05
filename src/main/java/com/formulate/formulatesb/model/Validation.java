package com.formulate.formulatesb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Validation {
    private String message;
    private String type = "regex"; // Default to 'regex'
    private String pattern;
}