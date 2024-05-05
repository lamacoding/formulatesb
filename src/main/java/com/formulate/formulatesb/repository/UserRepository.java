package com.formulate.formulatesb.repository;

import com.formulate.formulatesb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface UserRepository extends MongoRepository<User, String> {
    public User findByEmail(String email);
}
