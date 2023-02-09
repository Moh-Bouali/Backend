package com.s3.individual.s3individual.Business;

import ENUMS.RolesEnum;
import com.s3.individual.s3individual.Interfaces.PersonRepo;
import com.s3.individual.s3individual.Domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonManagerIMPLTest {
    @Mock
    private PersonRepo personRepo;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PersonManagerIMPL personManagerIMPL;
    @Test
    void returnPersonById() {
        Person person = Person.builder().id(5l).name("test").email("test").password("test").address("test").build();
        when(personRepo.returnPerson(person.getId())).thenReturn(Optional.ofNullable(person));
        Optional<Person> returnedPerson = personRepo.returnPerson(person.getId());
        assertEquals(person.getId(),returnedPerson.get().getId());
    }
    @Test
    void updatePerson() {
        Person personToUpdate = Person.builder().id(7l).name("").email("testEmailUpdated").password("").address("").build();
        doNothing().when(personRepo).updatePerson(personToUpdate);
        personManagerIMPL.updatePerson(personToUpdate);
        verify(personRepo,times(1)).updatePerson(personToUpdate);
    }
    @Test
    void savePerson() {
        Person personToSave = Person.builder().role(RolesEnum.CLIENT.toString()).address("add").email("email").password("password").name("name").build();
        doNothing().when(personRepo).savePerson(personToSave);
        Mockito.when(passwordEncoder.encode(personToSave.getPassword())).thenReturn(new BCryptPasswordEncoder().toString());
        personManagerIMPL.savePerson(personToSave);
        verify(personRepo,times(1)).savePerson(personToSave);
    }
}