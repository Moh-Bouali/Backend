package com.s3.individual.s3individual.Interfaces;


import com.s3.individual.s3individual.Domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
