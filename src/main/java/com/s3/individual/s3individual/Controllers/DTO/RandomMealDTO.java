package com.s3.individual.s3individual.Controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RandomMealDTO {
    String mainCourse;
    String appetizer;
    String drink;
    String dessert;
}