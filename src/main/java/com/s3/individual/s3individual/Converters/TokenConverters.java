package com.s3.individual.s3individual.Converters;

import com.s3.individual.s3individual.Controllers.DTO.TokenResponseDTO;
import com.s3.individual.s3individual.Domain.Person;
import com.s3.individual.s3individual.Domain.TokenResponse;
import com.s3.individual.s3individual.Persistance.Entity.PersonEntity;

public class TokenConverters {
    public static TokenResponseDTO convertToDTO(TokenResponse tokenResponse){
        return TokenResponseDTO.builder()
                .accessToken(tokenResponse.getAccessToken())
                .build();
    }
}
