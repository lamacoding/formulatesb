package com.formulate.formulatesb.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;

    @Length(min = 2)
    private String firstname;
    @Length(min = 2)
    private String lastname;
    @NotBlank
    @Indexed(unique = true)
    @Email
    private String email;
    @Length(min = 8)
    private String password;
    @Nullable
    private List<String> ownedForms;
}
