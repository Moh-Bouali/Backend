package com.s3.individual.s3individual.Domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllRestaurants {
    private List<Restaurant> restaurantList;
}
