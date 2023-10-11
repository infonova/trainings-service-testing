package com.bearingpoint.training.demoapplication.controller;

import com.bearingpoint.training.demoapplication.dto.Person;
import com.bearingpoint.training.demoapplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/addPerson")
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        personService.storePerson(person);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/persons")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = personService.getStoredPersons();
        return ResponseEntity.ok(persons);
    }

    @DeleteMapping(path = "/persons")
    public ResponseEntity<List<Person>> deletePersons() {
        personService.deletePerson();
        return ResponseEntity.ok().build();
    }
}
