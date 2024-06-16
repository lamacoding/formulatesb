package com.formulate.formulatesb.service;

import com.formulate.formulatesb.dto.FormCreateDto;
import com.formulate.formulatesb.model.Form;
import com.formulate.formulatesb.model.Session;
import com.formulate.formulatesb.model.User;
import com.formulate.formulatesb.repository.FormRepository;
import com.formulate.formulatesb.repository.SessionRepository;
import com.formulate.formulatesb.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormService {
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionService sessionService;

    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    public Form getFormById(String id) {
        return formRepository.findById(id).orElse(null); // Handle potential 'not found' case
    }

    public Form createForm(FormCreateDto formCreateDto) {
        // The explicit creation of an ObjectID is necessary as it is needed in the User ownedForms as well.
        ObjectId id = new ObjectId();
        Form form = new Form();
        form.setId(id);
        form.setFormName(formCreateDto.getFormName());
        User owner = sessionService.getUserBySessionId(formCreateDto.getSessionId());

        if (owner == null) {
            return null;
        }
        List<String> ownedForms = owner.getOwnedForms();
        if (ownedForms == null) {
            ownedForms = new ArrayList<>();
            owner.setOwnedForms(ownedForms);
        }
        ownedForms.add(id.toHexString());
        owner.setOwnedForms(ownedForms);
        userRepository.save(owner);
        form.setOwnerId(owner.getId().toHexString());
        return formRepository.save(form);
    }

    public Form updateForm(String id, Form updatedForm) {
        Optional<Form> formOptional = formRepository.findById(id);
        if (formOptional.isEmpty()) {
            return null;
        }
        ObjectId objectId = new ObjectId(id);
        updatedForm.setId(objectId); // Ensure the updatedForm retains the original ID
        return formRepository.save(updatedForm);
    }

    public boolean deleteForm(String id) {
        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(user -> user.getOwnedForms().contains(id))
                .findAny();

        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        user.getOwnedForms().remove(id);
        userRepository.save(user);

        formRepository.deleteById(id);

        return true;
    }

    public List<Form> getAllFormsBySessionId(String sessionId) {
        Session session = sessionRepository.findById(sessionId).orElse(null);

        if (session == null) {
            return null;
        }

        boolean isValid = sessionService.getSessionValidity(sessionId);

        if (!isValid) {
            return null;
        }

        User user = sessionService.getUserBySessionId(sessionId);
        List<String> ownedForms = user.getOwnedForms();
        if (ownedForms == null) {
            return null;
        }
        if (ownedForms.isEmpty()) {
            return null;
        }
        return formRepository.findAllById(ownedForms);
    }
}