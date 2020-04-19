package ir.ac.ut.ie.CA_06_mzFoodDelivery.domain.MzFoodDelivery.Exceptions;

public class RestaurantIsNotNearUserException extends Exception {
    // Parameterless Constructor
    public RestaurantIsNotNearUserException() {}

    // Constructor that accepts a message
    public RestaurantIsNotNearUserException(String message)
    {
        super(message);
    }
}
