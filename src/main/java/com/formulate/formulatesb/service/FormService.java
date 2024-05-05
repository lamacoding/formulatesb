package com.formulate.formulatesb.service;

import com.formulate.formulatesb.model.Form;
import com.formulate.formulatesb.model.User;
import com.formulate.formulatesb.repository.FormRepository;
import com.formulate.formulatesb.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public Form getFormById(String id) {
        return formRepository.findById(id).orElse(null); // Handle potential 'not found' case
    }

    public Form createForm(Form form) {
        User user = userRepository.findById(form.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Form savedForm = formRepository.save(form);
        user.getOwnedForms().add(savedForm.getId().toString());
        userRepository.save(user);

        return savedForm;
    }

    public Form updateForm(String id, Form updatedForm) {
        // Add logic to ensure the form with 'id' exists if needed
        ObjectId objectId = new ObjectId(id);
        updatedForm.setId(objectId); // Ensure the updatedForm retains the original ID
        return formRepository.save(updatedForm);
    }

    public void deleteForm(String id) {
        formRepository.deleteById(id);
    }
}