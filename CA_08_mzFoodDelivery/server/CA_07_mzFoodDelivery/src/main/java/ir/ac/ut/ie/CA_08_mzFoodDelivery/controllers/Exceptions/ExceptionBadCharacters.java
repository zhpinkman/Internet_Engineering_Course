package ir.ac.ut.ie.CA_08_mzFoodDelivery.controllers.Exceptions;

public class ExceptionBadCharacters extends Exception{
    @Override
    public String getMessage() {
        return "Illegal use of tags or special characters";
    }
}
