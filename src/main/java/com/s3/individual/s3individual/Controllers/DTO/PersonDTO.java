package com.s3.individual.s3individual.Controllers.DTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO{
    private Long id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String role;
}

