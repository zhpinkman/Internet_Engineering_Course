package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Exceptions;

public class RestaurantNotFoundException extends Exception{
    // Parameterless Constructor
    public RestaurantNotFoundException() {}

    // Constructor that accepts a message
    public RestaurantNotFoundException(String message)
    {
        super(message);
    }
}
