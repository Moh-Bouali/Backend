package com.s3.individual.s3individual.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    Long menuId;
    Long restaurantId;
    String name;
    String price;
    String type;
}
