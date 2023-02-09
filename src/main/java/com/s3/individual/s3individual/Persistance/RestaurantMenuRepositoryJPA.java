package com.s3.individual.s3individual.Persistance;

import com.s3.individual.s3individual.Persistance.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RestaurantMenuRepositoryJPA extends JpaRepository<MenuEntity, Long> {
    List<MenuEntity> findMenuEntitiesByRestaurant_Id(Long id);

    @Transactional
    @Query(value = "SELECT name FROM menu_restaurant WHERE restaurant_id = :id AND type = :type ORDER BY RAND() LIMIT 1 ", nativeQuery = true)
    String returnRandomMeal(Long id,String type);
}
