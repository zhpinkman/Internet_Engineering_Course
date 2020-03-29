package schedulers;

import MzFoodDelivery.Delivery.Order;

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
            this.order.setDeliveredStatus();
        }
    }
}
