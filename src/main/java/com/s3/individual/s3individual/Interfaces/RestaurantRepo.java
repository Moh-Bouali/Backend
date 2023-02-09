package com.s3.individual.s3individual.Interfaces;

import com.s3.individual.s3individual.Domain.Menu;
import com.s3.individual.s3individual.Domain.Restaurant;
import com.s3.individual.s3individual.Persistance.Entity.RestaurantEntity;

import java.util.List;

public interface RestaurantRepo {
    List<Restaurant> returnAllRestaurants();
    List<Menu> returnRestaurantMenu(Long id);
    String returnMeal(Long id, String type);
}
