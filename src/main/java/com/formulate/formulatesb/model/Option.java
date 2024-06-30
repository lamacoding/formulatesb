package com.formulate.formulatesb.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Option {
    @Id
    private String id;
    private String value;
//
//    public Option() {
//        this.id = new ObjectId();
//    }
//
//    public Option(String value) {
//        this.id = new ObjectId();
//        this.value = value;
//    }
}