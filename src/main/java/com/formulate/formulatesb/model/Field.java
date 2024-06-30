package com.formulate.formulatesb.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class Field {
    @NotBlank
    private String label;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private List<Option> options; //for dropdown, multi-select-dropdown, checkbox and radio
    private Validation validation;
}