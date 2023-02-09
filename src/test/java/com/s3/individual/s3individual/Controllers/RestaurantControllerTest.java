package com.s3.individual.s3individual.Controllers;

import com.s3.individual.s3individual.Domain.AccessToken;
import com.s3.individual.s3individual.Domain.AllRestaurants;
import com.s3.individual.s3individual.Domain.Restaurant;
import com.s3.individual.s3individual.Interfaces.AccessTokenDecoder;
import com.s3.individual.s3individual.Interfaces.RestaurantManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
@ContextConfiguration
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestaurantManager restaurantManagerMock;
    @MockBean private AccessToken accessToken;
    @MockBean private AccessTokenDecoder accessTokenDecoder;
    @Test
    void getAllRestaurants() throws Exception {
        AllRestaurants response = AllRestaurants.builder().restaurantList(List.of(
                Restaurant.builder().id(5L).name("Czech Food").address("CzechLaan").owner("Kuba").url("url").build(),
                Restaurant.builder().id(6L).name("Dutch Food").address("Helmond").owner("Lex").url("url").build()
        )).build();
        when(restaurantManagerMock.returnAllRestaurants()).thenReturn(response);
        mockMvc.perform(get("/restaurants")).andDo(print()).andExpect(status().isOk()).andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE)).andExpect(content().json("""
            {"restaurantDTOList":[{"id":5,"name":"Czech Food","address":"CzechLaan","owner":"Kuba","url":"url"},{"id":6,"name":"Dutch Food","address":"Helmond","owner":"Lex","url":"url"}]}
        """));
        verify(restaurantManagerMock).returnAllRestaurants();
    }
}