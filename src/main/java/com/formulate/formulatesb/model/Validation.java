package com.formulate.formulatesb.model;

import lombok.Data;

@Data
public class Validation {
    private String message; // Error message if input does not fit regex pattern
    private String pattern; // Regex pattern
    private Boolean isRequired;
}