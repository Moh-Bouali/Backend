package com.s3.individual.s3individual.Domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllPersons {
    private List<Person> personList;
}
