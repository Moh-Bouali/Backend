package com.s3.individual.s3individual.Converters;

import com.s3.individual.s3individual.Controllers.DTO.PersonDTO;
import com.s3.individual.s3individual.Domain.Person;
import com.s3.individual.s3individual.Persistance.Entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PersonConventer {

    private PersonConventer(){}
    public static Person convertToDomain(PersonEntity person){
        return Person.builder()
                .id(person.getId())
                .name(person.getName())
                .address(person.getAddress())
                .email(person.getEmail())
                .password(person.getPassword())
                .role(person.getRole())
                .build();
    }
    public static PersonEntity convertToEntity(Person person){
        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .address(person.getAddress())
                .email(person.getEmail())
                .password(person.getPassword())
                .role(person.getRole())
                .build();
    }
    public static PersonDTO convertToDTO(Person person){
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .address(person.getAddress())
                .email(person.getEmail())
                .role(person.getRole())
                .build();
    }
    public static Person convertToDomainFromDTO(PersonDTO persondto){
        return Person.builder()
                .id(persondto.getId())
                .name(persondto.getName())
                .address(persondto.getAddress())
                .email(persondto.getEmail())
                .password(persondto.getPassword())
                .role(persondto.getRole())
                .build();
    }
}

