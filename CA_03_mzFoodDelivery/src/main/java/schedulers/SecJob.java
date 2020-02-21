package schedulers;

import HTTPRequestHandler.HTTPRequestHandler;
import MzFoodDelivery.Delivery;
import MzFoodDelivery.MzFoodDelivery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SecJob implements Runnable {
    @Override
    public void run() {
        System.out.println("zhivar");
    }


    public void importDeliveriesFromWeb() throws Exception {
        String deliveriesJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:8080/deliveries");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Delivery> deliveries = gson.fromJson(deliveriesJsonString, new TypeToken<List<Delivery>>() {
        }.getType());
        int counter = 1;
        for (Delivery delivery : deliveries) {
            System.out.println(counter + "----------------");
            counter++;
            try {
                MzFoodDelivery.getInstance().addDelivery(delivery);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
