package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers;

import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodPartyController {

    @RequestMapping(
            value = "/foodParty",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PartyFood restaurants() {
        return MzFoodDelivery.getInstance().getPartyFoods().get(0);
    }
}
