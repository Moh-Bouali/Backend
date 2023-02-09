package com.s3.individual.s3individual.Interfaces;

import com.s3.individual.s3individual.Domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
