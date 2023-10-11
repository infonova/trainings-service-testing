package com.bearingpoint.training.demoapplication.service;

import com.bearingpoint.training.demoapplication.dto.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<Person> persons = new ArrayList<>();

    public void storePerson(Person person) {
        persons.add(person);
    }

    public List<Person> getStoredPersons() {
        return persons;
    }

    public void deletePerson(){
        persons = new ArrayList<>();
    }
}
