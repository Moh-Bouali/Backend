package com.s3.individual.s3individual.Persistance.Entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu_restaurant")
@Builder
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2 ,max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Length(min = 2 ,max = 50)
    @Column(name = "price")
    private Double price;

    @NotBlank
    @Length(min = 2 ,max = 50)
    @Column(name = "type")
    private String type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;
}
