package com.s3.individual.s3individual.Domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String role;
    public void setAddress(String address){
        this.address = address;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword (String password){
        this.password = password;
    }
    public void setRole (String role){
        this.role = role;
    }
}