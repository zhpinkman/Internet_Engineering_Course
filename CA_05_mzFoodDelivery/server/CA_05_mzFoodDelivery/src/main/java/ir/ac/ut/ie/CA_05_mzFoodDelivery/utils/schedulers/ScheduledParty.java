package ir.ac.ut.ie.CA_05_mzFoodDelivery.utils.schedulers;


import ir.ac.ut.ie.CA_05_mzFoodDelivery.domain.MzFoodDelivery.MzFoodDelivery;

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