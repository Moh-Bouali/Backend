package com.s3.individual.s3individual.Business;

import ENUMS.RolesEnum;
import com.s3.individual.s3individual.Domain.AccessToken;
import com.s3.individual.s3individual.Domain.TokenResponse;
import com.s3.individual.s3individual.Business.exception.UnauthorizedDataAccessException;
import com.s3.individual.s3individual.Interfaces.PersonManager;
import com.s3.individual.s3individual.Interfaces.PersonRepo;
import com.s3.individual.s3individual.Domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Component
@Service("FirstPerson")
@RequiredArgsConstructor
public class PersonManagerIMPL implements PersonManager {
    private final PersonRepo personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessToken requestAccessToken;
    @Override
    public Optional<Person> returnPersonById(long id) {
        if (requestAccessToken.hasRole(RolesEnum.CLIENT.toString())) {
            if (requestAccessToken.getPersonId() != id) {
                throw new UnauthorizedDataAccessException("PERSON_ID_NOT_FROM_LOGGED_IN_USER");
            }
        }
        return personRepository.returnPerson(id);
    }
    @Override
    public void savePerson(Person person) {
        String password = passwordEncoder.encode(person.getPassword());
        person.setPassword(password);
        person.setRole(RolesEnum.CLIENT.toString());
        personRepository.savePerson(person);
    }
    @Override
    public void updatePerson(Person person){
        for (Person p : personRepository.returnAllPersons()){
            if(p.getId().toString().equals(person.getId().toString())){
                person.setPassword(p.getPassword());
                person.setRole(p.getRole());
                if(person.getAddress() == ""){
                    person.setAddress(p.getAddress());
                }
                if(person.getName() == ""){
                    person.setName(p.getName());
                }
                if(person.getEmail() == ""){
                    person.setEmail(p.getEmail());
                }
                break;
            }
        }
        personRepository.updatePerson(person);
    }

    @Override
    public TokenResponse checkLogin(Person person){
        return personRepository.checkLogin(person);
    }
}
