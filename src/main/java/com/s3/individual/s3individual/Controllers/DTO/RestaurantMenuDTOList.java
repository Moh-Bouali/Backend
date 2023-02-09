package com.s3.individual.s3individual.Controllers.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantMenuDTOList {
    private List<RestaurantMenuDTO> restaurantMenuDTOList;
}
