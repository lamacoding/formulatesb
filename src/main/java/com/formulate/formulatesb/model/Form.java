package com.formulate.formulatesb.model;

import com.formulate.formulatesb.dto.FormDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "forms")
public class Form {
    @Id
    private ObjectId id;

    @NotBlank
    private String formName;
    @Pattern(regexp = "\\b[A-Fa-f0-9]{24}\\b") // ObjectId is a 24 character long hex string
    private String ownerId;
    private List<Field> fields;

    public FormDto mapToFormDto() {
        FormDto formDto = new FormDto();
        formDto.setId(this.id.toHexString());
        formDto.setFormName(this.formName);
        formDto.setOwnerId(this.ownerId);
        List<Field> fields = new ArrayList<>(this.fields);
        formDto.setFields(fields);
        return formDto;
    }
}