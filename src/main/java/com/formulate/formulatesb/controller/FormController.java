package com.formulate.formulatesb.controller;

import com.formulate.formulatesb.model.Form;
import com.formulate.formulatesb.service.FormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/form")
public class FormController {
    @Autowired
    private FormService formService;
    // private final String currentUser = "6636beed0e66743f9cefa30f";

    @GetMapping("/all")
    public ResponseEntity<List<Form>> getAll() {
        return new ResponseEntity<>(formService.getAllForms(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Form> getById(@PathVariable String id) {
        return new ResponseEntity<>(formService.getFormById(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Form> create(@Valid @RequestBody Form form) {
        return new ResponseEntity<>(formService.createForm(form), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Form> update(@PathVariable String id, @Valid @RequestBody Form form) {
        return new ResponseEntity<>(formService.updateForm(id, form), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        boolean deleted = formService.deleteForm(id);
        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
