package com.s3.individual.s3individual.Interfaces;

import com.s3.individual.s3individual.Domain.Person;
import com.s3.individual.s3individual.Domain.TokenResponse;

import java.io.InvalidObjectException;
import java.util.Optional;

public interface PersonManager {
    Optional<Person> returnPersonById(long id);
    void savePerson(Person person);
    void updatePerson(Person person);
    TokenResponse checkLogin(Person person);
}