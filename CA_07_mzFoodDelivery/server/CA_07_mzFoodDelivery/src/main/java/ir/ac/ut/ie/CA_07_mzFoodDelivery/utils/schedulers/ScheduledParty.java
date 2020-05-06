package ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.schedulers;

import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;

public class ScheduledParty implements Runnable {

    public ScheduledParty(int period) {
        MzFoodDelivery.getInstance().setFoodPartyPeriod(period);
    }

    @Override
    public void run() {
        System.out.println("zhivar");
        try {
            MzFoodDelivery.getInstance().resetFoodPartyTimer();
            MzFoodDelivery.getInstance().importFoodPartyFromWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}