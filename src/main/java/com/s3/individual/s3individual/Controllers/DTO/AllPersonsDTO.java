package com.s3.individual.s3individual.Controllers.DTO;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllPersonsDTO {
    private List<PersonDTO> personList;
}
