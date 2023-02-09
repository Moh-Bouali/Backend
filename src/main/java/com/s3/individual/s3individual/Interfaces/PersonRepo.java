package com.s3.individual.s3individual.Interfaces;

import com.s3.individual.s3individual.Domain.Person;
import com.s3.individual.s3individual.Domain.TokenResponse;


import java.io.InvalidObjectException;
import java.util.List;
import java.util.Optional;

public interface PersonRepo {
    Optional<Person> returnPerson (long personId);
    void updatePerson(Person person);
    List<Person> returnAllPersons();
    void savePerson(Person person);
    TokenResponse checkLogin(Person person);
}
