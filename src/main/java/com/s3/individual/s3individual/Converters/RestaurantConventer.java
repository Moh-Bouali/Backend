package com.s3.individual.s3individual.Converters;

import com.s3.individual.s3individual.Controllers.DTO.RandomMealDTO;
import com.s3.individual.s3individual.Controllers.DTO.RestaurantDTO;
import com.s3.individual.s3individual.Controllers.DTO.RestaurantMenuDTO;
import com.s3.individual.s3individual.Domain.Menu;
import com.s3.individual.s3individual.Domain.RandomMeal;
import com.s3.individual.s3individual.Domain.Restaurant;
import com.s3.individual.s3individual.Persistance.Entity.MenuEntity;
import com.s3.individual.s3individual.Persistance.Entity.RestaurantEntity;

import java.util.List;


public class RestaurantConventer {
    private RestaurantConventer(){

    }
    public static Restaurant convert(RestaurantEntity restaurant){
        return Restaurant.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .owner(restaurant.getOwner())
                .url(restaurant.getUrl())
                .build();
    }
    public static Menu convertMenuToDomain(MenuEntity menuEntity){
        return Menu.builder()
                .restaurantId(menuEntity.getRestaurant().getId())
                .menuId(menuEntity.getId())
                .name(menuEntity.getName())
                .price(menuEntity.getPrice().toString())
                .type(menuEntity.getType())
                .build();
    }
    public static RestaurantDTO convertRestaurantToDTO(Restaurant restaurant){
        return RestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .owner(restaurant.getOwner())
                .url(restaurant.getUrl())
                .build();
    }
    public static RandomMealDTO convertMealtoDto(RandomMeal randomMeal){
        return RandomMealDTO.builder()
                .appetizer(randomMeal.getAppetizer())
                .dessert(randomMeal.getDessert())
                .drink(randomMeal.getDrink())
                .mainCourse(randomMeal.getMainCourse())
                .build();
    }
    public static RestaurantMenuDTO convertMenuToDTO(Menu menu){
        return RestaurantMenuDTO.builder()
                .restaurantId(menu.getRestaurantId())
                .menuId(menu.getMenuId())
                .name(menu.getName())
                .price(menu.getPrice())
                .type(menu.getType())
                .qty(0)
                .build();
    }
}