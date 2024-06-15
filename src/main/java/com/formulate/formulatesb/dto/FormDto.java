package com.formulate.formulatesb.dto;

import com.formulate.formulatesb.model.Field;
import com.formulate.formulatesb.model.Form;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class FormDto {
    @Id
    private String id;

    @NotBlank
    private String formName;
    @Pattern(regexp = "\\b[A-Fa-f0-9]{24}\\b") // ObjectId is a 24 character long hex string
    private String ownerId;
    private List<Field> fields;

//    public Form mapToForm() {
//        Form form = new Form();
//        form.setId(new ObjectId(this.id);
//        form.setFormName(this.formName);
//        form.setOwnerId(this.ownerId);
//        List<Field> fieldList = new ArrayList<>(this.fields);
//
//        return form;
//    }
}
