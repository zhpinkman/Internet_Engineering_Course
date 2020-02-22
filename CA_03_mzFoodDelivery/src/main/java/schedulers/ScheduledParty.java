package schedulers;

import MzFoodDelivery.MzFoodDelivery;

public class ScheduledParty implements Runnable {

    @Override
    public void run() {
        try {
            MzFoodDelivery.getInstance().importFoodPartyFromWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}