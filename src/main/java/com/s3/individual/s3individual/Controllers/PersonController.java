package com.s3.individual.s3individual.Controllers;

import com.s3.individual.s3individual.Business.exception.EmailNotFoundException;
import com.s3.individual.s3individual.Business.exception.PasswordIncorrectException;
import com.s3.individual.s3individual.Controllers.DTO.TokenResponseDTO;
import com.s3.individual.s3individual.Converters.PersonConventer;
import com.s3.individual.s3individual.Converters.TokenConverters;
import com.s3.individual.s3individual.Domain.AccessToken;
import com.s3.individual.s3individual.Domain.TokenResponse;
import com.s3.individual.s3individual.Interfaces.PersonManager;
import com.s3.individual.s3individual.Controllers.DTO.PersonDTO;
import com.s3.individual.s3individual.Domain.Person;
import com.s3.individual.s3individual.security.isauthenticated.IsAuthenticated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonManager personManager;

    private AccessToken accessToken;

    public PersonController(@Qualifier("FirstPerson") PersonManager personManager, AccessToken accessToken){
        this.personManager = personManager;
        this.accessToken = accessToken;
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_CLIENT"})
    @GetMapping("/currentLoggedInPerson")
    public ResponseEntity<PersonDTO> getPerson(){
        final Optional<PersonDTO> personOptional = personManager.returnPersonById(accessToken.getPersonId()).map(PersonConventer::convertToDTO);
        if (personOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(personOptional.get());
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> checkLogin(@RequestBody PersonDTO personDTO){
        try{
            Person personToCheck = PersonConventer.convertToDomainFromDTO(personDTO);
            TokenResponse response = personManager.checkLogin(personToCheck);
            TokenResponseDTO responseDTO = TokenConverters.convertToDTO(response);
            return ResponseEntity.ok(responseDTO);
        }
        catch (PasswordIncorrectException | EmailNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @IsAuthenticated
    @RolesAllowed({"ROLE_CLIENT"})
    @PutMapping("/updatePerson")
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDto){
        Person personWithLatestUpdates = PersonConventer.convertToDomainFromDTO(personDto);
        personManager.updatePerson(personWithLatestUpdates);
        final Optional<PersonDTO> updatedPerson = personManager.returnPersonById(personDto.getId()).map(PersonConventer::convertToDTO);
        if (updatedPerson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedPerson.get());
    }
    @PutMapping()
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO){
        Person personToAdd = PersonConventer.convertToDomainFromDTO(personDTO);
        personManager.savePerson(personToAdd);
        return ResponseEntity.ok(PersonConventer.convertToDTO(personToAdd));
    }
}