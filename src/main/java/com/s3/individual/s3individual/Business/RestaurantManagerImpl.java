package com.s3.individual.s3individual.Business;

import ENUMS.MealTypeEnums;
import com.s3.individual.s3individual.Domain.*;
import com.s3.individual.s3individual.Interfaces.RestaurantManager;
import com.s3.individual.s3individual.Interfaces.RestaurantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantManagerImpl implements RestaurantManager {
    private final RestaurantRepo restaurantRepository;

    @Override
    public AllRestaurants returnAllRestaurants(){
        List<Restaurant> allRestaurants;
        allRestaurants = restaurantRepository.returnAllRestaurants();
        final AllRestaurants response = new AllRestaurants();
        response.setRestaurantList(allRestaurants);
        return response;
    }

    @Override
    public RestaurantMenuList returnRestaurantMenu(Long id) {
        List<Menu> menuList;
        menuList = restaurantRepository.returnRestaurantMenu(id);
        final RestaurantMenuList response = new RestaurantMenuList();
        response.setRestaurantMenuList(menuList);
        return response;
    }

    @Override
    public RandomMeal returnARandomMeal(Long id) {
        RandomMeal meal = RandomMeal.builder().appetizer(restaurantRepository.returnMeal(id, MealTypeEnums.appetizer.toString())).mainCourse(restaurantRepository.returnMeal(id, "main course")).dessert(restaurantRepository.returnMeal(id, MealTypeEnums.dessert.toString())).drink(restaurantRepository.returnMeal(id, MealTypeEnums.drink.toString()))
                .build();
        return meal;
    }
}