package com.s3.individual.s3individual.Interfaces;

import com.s3.individual.s3individual.Domain.AllRestaurants;
import com.s3.individual.s3individual.Domain.RestaurantMenuList;
import com.s3.individual.s3individual.Domain.RandomMeal;

public interface RestaurantManager {
    AllRestaurants returnAllRestaurants();
    RestaurantMenuList returnRestaurantMenu(Long id);
    RandomMeal returnARandomMeal(Long id);
}
