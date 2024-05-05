package com.formulate.formulatesb.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    @NotBlank
    private String label;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private Validation validation;
}