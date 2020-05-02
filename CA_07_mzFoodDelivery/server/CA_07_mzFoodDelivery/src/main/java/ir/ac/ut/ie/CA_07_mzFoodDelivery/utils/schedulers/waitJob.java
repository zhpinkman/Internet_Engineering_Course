package ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.schedulers;



import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;

import java.sql.SQLException;
import java.util.concurrent.ScheduledExecutorService;

public class waitJob implements Runnable {

    private ScheduledExecutorService scheduler;
    private boolean visited = false;
    private Order order;

    public waitJob(ScheduledExecutorService scheduler, Order order) {
        this.scheduler = scheduler;
        this.order = order;
    }

    @Override
    public void run() {
        if (!visited) {
            System.out.println("shit");
            visited = true;
        } else {
            BackgroundJobManager.stopJob(scheduler);
            System.out.println("stopped");
            try {
                this.order.setDeliveredStatus();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
