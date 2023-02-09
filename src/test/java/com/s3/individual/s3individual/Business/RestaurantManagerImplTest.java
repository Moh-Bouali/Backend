package com.s3.individual.s3individual.Business;

import ENUMS.MealTypeEnums;
import com.s3.individual.s3individual.Domain.*;
import com.s3.individual.s3individual.Interfaces.RestaurantRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantManagerImplTest {

    @Mock
    private RestaurantRepo restaurantRepo;

    @InjectMocks
    private RestaurantManagerImpl restaurantManagerImpl;

    @Test
    void returnAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
            restaurants.add(Restaurant.builder().id(1L).address("address1").name("name1").owner("owner1").build());
            restaurants.add(Restaurant.builder().id(2L).address("address2").name("name2").owner("owner2").build());
            restaurants.add(Restaurant.builder().id(3L).address("address3").name("name3").owner("owner3").build());

            when(restaurantRepo.returnAllRestaurants()).thenReturn(restaurants);
        AllRestaurants response = restaurantManagerImpl.returnAllRestaurants();
        assertEquals(response.getRestaurantList().stream().count(), restaurants.stream().count());
    }
    @Test
    void returnRestaurantMenu() {
        List<Menu> menuList = new ArrayList<>();
        menuList.add(Menu.builder().menuId(1L).type(MealTypeEnums.drink.toString()).price("3").name("test").restaurantId(1L).build());
        menuList.add(Menu.builder().menuId(2L).type(MealTypeEnums.dessert.toString()).price("2").name("test").restaurantId(1L).build());
        menuList.add(Menu.builder().menuId(3L).type(MealTypeEnums.appetizer.toString()).price("2").name("test").restaurantId(1L).build());

        when(restaurantRepo.returnRestaurantMenu(1L)).thenReturn(menuList);

        RestaurantMenuList response = restaurantManagerImpl.returnRestaurantMenu(1L);
        assertEquals(response.getRestaurantMenuList().stream().count(), menuList.stream().count());
    }
    @Test
    void returnARandomMeal() {
        String appetizer = "salad";
        String mainCourse = "tacos";
        String dessert = "fruitSalad";
        String drink = "pepsi";
        RandomMeal meal = RandomMeal.builder().appetizer(appetizer).mainCourse(mainCourse).dessert(dessert).drink(drink)
                    .build();
        when(restaurantRepo.returnMeal(1L,appetizer)).thenReturn(meal.getAppetizer());
        when(restaurantRepo.returnMeal(1L,mainCourse)).thenReturn(meal.getMainCourse());
        when(restaurantRepo.returnMeal(1L,dessert)).thenReturn(meal.getDessert());
        when(restaurantRepo.returnMeal(1L,drink)).thenReturn(meal.getDrink());

        String returnedAppetizer = restaurantRepo.returnMeal(1L,meal.getAppetizer());
        assertEquals(meal.getAppetizer(),returnedAppetizer);

        String returnedMainCourse = restaurantRepo.returnMeal(1L,meal.getMainCourse());
        assertEquals(meal.getMainCourse(),returnedMainCourse);

        String returnedDessert = restaurantRepo.returnMeal(1L,meal.getDessert());
        assertEquals(meal.getDessert(),returnedDessert);

        String returnedDrink = restaurantRepo.returnMeal(1L,meal.getDrink());
        assertEquals(meal.getDrink(),returnedDrink);
    }
}