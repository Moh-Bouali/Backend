package com.s3.individual.s3individual.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RandomMeal {
    String mainCourse;
    String appetizer;
    String drink;
    String dessert;
}
