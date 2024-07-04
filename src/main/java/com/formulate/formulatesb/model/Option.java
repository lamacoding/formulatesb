package com.formulate.formulatesb.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Option {
    @Id
    private String id;
    private String value;
}