package ir.ac.ut.ie.CA_05_mzFoodDelivery.utils.schedulers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.utils.HTTPRequestHandler.HTTPRequestHandler;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Delivery;
import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class SecJob implements Runnable {

    private ScheduledExecutorService scheduler;

    public SecJob(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        System.out.println("running");
        if (MzFoodDelivery.getInstance().getDeliveries().size() != 0) {
            BackgroundJobManager.stopJob(scheduler);
            MzFoodDelivery.getInstance().assignDeliveryToOrder();
            System.out.println("finished");
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
