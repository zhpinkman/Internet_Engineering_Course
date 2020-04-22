package ir.ac.ut.ie.CA_06_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodPartyController {

    @RequestMapping(
            value = "/partyFoods",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PartyFood> PartyFoods() {
        List<PartyFood> partyFoods = MzFoodDelivery.getInstance().getPartyFoods();
//        System.out.println(partyFoods.size());
        return partyFoods;
    }

    @RequestMapping(
            value = "/partyFoodsRemainingTime",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public long RemainingTime() {
        return MzFoodDelivery.getInstance().getFoodPartyRemainingTime();
    }
}
