package com.s3.individual.s3individual.Controllers.DTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    Long id;
    String name;
    String address;
    String owner;
    String url;
}
