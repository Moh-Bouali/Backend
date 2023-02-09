package com.s3.individual.s3individual.Persistance.Impl;

import com.s3.individual.s3individual.Converters.RestaurantConventer;
import com.s3.individual.s3individual.Domain.Menu;
import com.s3.individual.s3individual.Interfaces.RestaurantRepo;
import com.s3.individual.s3individual.Domain.Restaurant;
import com.s3.individual.s3individual.Persistance.Entity.RestaurantEntity;
import com.s3.individual.s3individual.Persistance.RestaurantMenuRepositoryJPA;
import com.s3.individual.s3individual.Persistance.RestaurantRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
@Repository
@Primary
@RequiredArgsConstructor
public class RestaurantRepoIMPL implements RestaurantRepo {
    private final RestaurantRepositoryJPA restaurantRepositoryJPA;
    private final RestaurantMenuRepositoryJPA restaurantMenuRepositoryJPA;
    public List<Restaurant> returnAllRestaurants(){
        return restaurantRepositoryJPA.findAll().stream().map(RestaurantConventer::convert).toList();
    }

    @Override
    public List<Menu> returnRestaurantMenu(Long id) {
        return restaurantMenuRepositoryJPA.findMenuEntitiesByRestaurant_Id(id).stream().map(RestaurantConventer::convertMenuToDomain).toList();
    }

    @Override
    public String returnMeal(Long id, String type) {
        return restaurantMenuRepositoryJPA.returnRandomMeal(id,type);
    }

}
