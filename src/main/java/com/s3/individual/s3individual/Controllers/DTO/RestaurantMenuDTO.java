package com.s3.individual.s3individual.Controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenuDTO {
    Long menuId;
    Long restaurantId;
    String name;
    String price;
    String type;
    Integer qty;
}
