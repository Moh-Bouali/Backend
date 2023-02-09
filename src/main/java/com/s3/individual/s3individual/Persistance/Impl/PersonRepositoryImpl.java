package com.s3.individual.s3individual.Persistance.Impl;

import ENUMS.RolesEnum;
import com.s3.individual.s3individual.Business.exception.CustomException;
import com.s3.individual.s3individual.Business.exception.EmailNotFoundException;
import com.s3.individual.s3individual.Business.exception.PasswordIncorrectException;
import com.s3.individual.s3individual.Business.exception.PersonNotFoundException;
import com.s3.individual.s3individual.Converters.PersonConventer;
import com.s3.individual.s3individual.Domain.AccessToken;
import com.s3.individual.s3individual.Domain.TokenResponse;
import com.s3.individual.s3individual.Interfaces.AccessTokenEncoder;
import com.s3.individual.s3individual.Interfaces.PersonRepo;
import com.s3.individual.s3individual.Domain.Person;
import com.s3.individual.s3individual.Persistance.Entity.PersonEntity;
import com.s3.individual.s3individual.Persistance.PersonRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
@Primary
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepo {

    private final PasswordEncoder passwordEncoder;
    private final PersonRepositoryJPA personRepositoryJPA;
    private final List<PersonEntity> savedPersons;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public Optional<Person> returnPerson (long personId) {
        return personRepositoryJPA.findById(personId).map(PersonConventer:: convertToDomain);
    }
    @Override
    public void updatePerson(Person person){
        for(PersonEntity p : personRepositoryJPA.findAll()){
            if(p.getId().toString().equals(person.getId().toString())){
                personRepositoryJPA.updatePerson(person.getId(), person.getName(), person.getAddress(), person.getEmail(), person.getPassword(), person.getRole());
            }
        }
    }
    @Override
    public List<Person> returnAllPersons(){
        return personRepositoryJPA.findAll().stream().map(PersonConventer :: convertToDomain).toList();
    }
    @Override
    public void savePerson(Person person) {
        personRepositoryJPA.save(PersonConventer.convertToEntity(person));
    }
    @Override
    public TokenResponse checkLogin(Person person){
        PersonEntity personEntity = personRepositoryJPA.findByEmail(person.getEmail());
        List<String> roles = new ArrayList<>();

        if(personEntity == null){
            throw new EmailNotFoundException();
        }
        if(!passwordEncoder.matches(person.getPassword(), personEntity.getPassword())){
            throw new PasswordIncorrectException();
        }
        if(personEntity.getId().equals(null)){
            throw new PersonNotFoundException();
        }
        roles.add(RolesEnum.CLIENT.toString());
        String accessToken =  accessTokenEncoder.encode(AccessToken.builder().subject(personEntity.getEmail()).personId(personEntity.getId())
                        .roles(roles).build());
        return TokenResponse.builder().accessToken(accessToken).build();
        //return null;
    }

}