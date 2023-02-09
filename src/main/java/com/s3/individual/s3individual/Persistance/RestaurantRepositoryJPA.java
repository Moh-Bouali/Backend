package com.s3.individual.s3individual.Persistance;


import com.s3.individual.s3individual.Persistance.Entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepositoryJPA extends JpaRepository<RestaurantEntity,Long> {
}