package ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.schedulers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.MzRepository;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.HTTPRequestHandler.HTTPRequestHandler;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Delivery;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;

import java.sql.SQLException;
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
        try {
            if (MzFoodDelivery.getInstance().getDeliveriesCount() != 0) {
                BackgroundJobManager.stopJob(scheduler);
                try {
                    MzFoodDelivery.getInstance().assignDeliveryToOrder();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("finished");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            MzRepository.getInstance().removeAllDeliveries();
//            MzFoodDelivery.getInstance().removeDeliveries();
            for (Delivery delivery: deliveries) {
                MzRepository.getInstance().addDelivery(delivery);
            }
//            MzFoodDelivery.getInstance().addDeliveries(deliveries);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
