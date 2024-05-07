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
    private String message; // Error message if input does not fit regex pattern
    private String pattern; // Regex pattern
    private Boolean isRequired;
}