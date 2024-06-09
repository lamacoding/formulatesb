package com.formulate.formulatesb.repository;

import com.formulate.formulatesb.model.Form;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormRepository extends MongoRepository<Form, String> {
}