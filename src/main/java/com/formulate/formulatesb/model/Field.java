package com.formulate.formulatesb.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private List<String> options; //for dropdown, multi-select-dropdown, checkbox and radio
    private Validation validation;
}