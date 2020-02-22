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
        System.out.println("searching for deliveries");
        if (MzFoodDelivery.getInstance().getDeliveries().size() != 0) {
            BackgroundJobManager.stopJob();
            System.out.println("stopping searching for deliveries");
        }
        try {
            importDeliveriesFromWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void importDeliveriesFromWeb() throws Exception {
        String deliveriesJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:8080/deliveries");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Delivery> deliveries = gson.fromJson(deliveriesJsonString, new TypeToken<List<Delivery>>() {
        }.getType());
        try {
            MzFoodDelivery.getInstance().removeDeliveries();
            MzFoodDelivery.getInstance().addDeliveries(deliveries);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
