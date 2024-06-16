package com.formulate.formulatesb.dto;

import com.formulate.formulatesb.model.Form;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FormCreateDto {
    @NotBlank
    public String formName;
    @NotBlank
    private String sessionId;

//    public Form mapToForm() {
//        Form form = new Form();
//        form.setFormName(formName);
//        form.setOwnerId();
//        return form;
//    }
}
