package com.s3.individual.s3individual.Controllers;

import com.s3.individual.s3individual.Controllers.DTO.*;
import com.s3.individual.s3individual.Converters.RestaurantConventer;
import com.s3.individual.s3individual.Interfaces.RestaurantManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantManager restaurantManager;
    @GetMapping()
    public ResponseEntity<AllRestaurantsDTO> getAllRestaurants(){
        final AllRestaurantsDTO response = new AllRestaurantsDTO();
        List<RestaurantDTO> restaurantDTOList = restaurantManager.returnAllRestaurants().getRestaurantList()
                .stream()
                .map(RestaurantConventer::convertRestaurantToDTO)
                .toList();
        response.setRestaurantDTOList(restaurantDTOList);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/restaurantMenu/{id}")
    public ResponseEntity<RestaurantMenuDTOList> getRestaurantMenu(@PathVariable(value = "id")final long id){
        final RestaurantMenuDTOList response = new RestaurantMenuDTOList();
        List<RestaurantMenuDTO> restaurantMenuDTOList = restaurantManager.returnRestaurantMenu(id).getRestaurantMenuList()
                .stream()
                .map(RestaurantConventer::convertMenuToDTO)
                .toList();
        response.setRestaurantMenuDTOList(restaurantMenuDTOList);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/randomMeal/{id}")
    public ResponseEntity<RandomMealDTO> getRandomMeal(@PathVariable(value = "id")final long id){
        final RandomMealDTO randomMealDTOOptional = RestaurantConventer.convertMealtoDto(restaurantManager.returnARandomMeal(id));
        if (randomMealDTOOptional == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(randomMealDTOOptional);
    }
    @MessageMapping("/orderStatus")
    @SendTo("/topic/status")
    public OrderStatusDTO getOrderStatus(NewStatusDTO newStatusDTO) throws InterruptedException {
        Thread.sleep(1000);
        return new OrderStatusDTO("Your order status is : " + HtmlUtils.htmlEscape(newStatusDTO.getNewStatusMessage()) + "!");
    }
}