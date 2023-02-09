package com.s3.individual.s3individual.Controllers;

import com.s3.individual.s3individual.Domain.AccessToken;
import com.s3.individual.s3individual.Domain.Person;
import com.s3.individual.s3individual.Interfaces.AccessTokenDecoder;
import com.s3.individual.s3individual.Interfaces.AccessTokenEncoder;
import com.s3.individual.s3individual.Interfaces.PersonManager;
import com.s3.individual.s3individual.Interfaces.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private PersonManager personManagerMock;
    @MockBean private PersonRepo personRepo;
    @MockBean private AccessToken accessToken;
    @MockBean private AccessTokenDecoder accessTokenDecoder;
    @MockBean private AccessTokenEncoder accessTokenEncoder;

    @Test
    @WithMockUser(username = "jakub@gmail.com", roles = {"CLIENT"})
    void getPerson() throws Exception{
        Person person = Person.builder().id(1L).name("Jakub Jelinak").address("Nanenstraat 22").email("jakub@gmail.com").password("jakub").role("CLIENT").build();

        when(personManagerMock.returnPersonById(accessToken.getPersonId())).thenReturn(Optional.of(person));
        mockMvc.perform(get("/persons/currentLoggedInPerson")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content()
                        .json("""
                                        {"id":1,"name":"Jakub Jelinak","address":"Nanenstraat 22","email":"jakub@gmail.com","password": null,"role":"CLIENT"}
                                        """));
        verify(personManagerMock).returnPersonById(accessToken.getPersonId());
    }
}