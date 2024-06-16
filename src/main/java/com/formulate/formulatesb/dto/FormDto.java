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

/*
    DTO used to send the id as String to the frontend and not as ObjectID (which would be an object).
    Form model contains the mapping to the DTO which just converts the ObjectID to a 24 character hex string.

    Reason it to keep the frontend independent of the used database because the conversion is done in the backend.
 */

@Data
public class FormDto {
    @Id
    private String id;

    @NotBlank
    private String formName;
    @Pattern(regexp = "\\b[A-Fa-f0-9]{24}\\b") // ObjectId is a 24 character long hex string
    private String ownerId;
    private List<Field> fields;
}
