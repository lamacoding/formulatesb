package com.formulate.formulatesb.controller;

import com.formulate.formulatesb.dto.FormCreateDto;
import com.formulate.formulatesb.dto.FormDto;
import com.formulate.formulatesb.model.Form;
import com.formulate.formulatesb.service.FormService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/form")
public class FormController {
    private static final Logger logger = LoggerFactory.getLogger(FormController.class);
    @Autowired
    private FormService formService;

    @GetMapping("/all")
    public ResponseEntity<List<Form>> getAll() {
        return new ResponseEntity<>(formService.getAllForms(), HttpStatus.OK);
    }

    @GetMapping("/all/bySessionId/{sessionId}")
    public ResponseEntity<List<FormDto>> getAllBySessionId(@PathVariable String sessionId) {
        logger.info("Form getAllBySessionId {}", sessionId);
        List<Form> forms = formService.getAllFormsBySessionId(sessionId);
        List<FormDto> formDtos = new ArrayList<>();

        if (forms != null) {
            for (Form form : forms) {
                formDtos.add(form.mapToFormDto());
            }
        }

        return new ResponseEntity<>(formDtos, formDtos.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Form> getById(@PathVariable String id) {
        logger.info("Form getById {}", id);
        return new ResponseEntity<>(formService.getFormById(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Form> create(@Valid @RequestBody FormCreateDto formCreateDto) {
        logger.info("Create form: {}", formCreateDto);
        Form form = formService.createForm(formCreateDto);
        return new ResponseEntity<>(form, form == null ? HttpStatus.FORBIDDEN : HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Form> update(@PathVariable String id, @Valid @RequestBody Form form) {
        logger.info("Update form: {}", form);
        return new ResponseEntity<>(formService.updateForm(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        logger.info("Delete form with id: {}", id);
        boolean deleted = formService.deleteForm(id);
        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
