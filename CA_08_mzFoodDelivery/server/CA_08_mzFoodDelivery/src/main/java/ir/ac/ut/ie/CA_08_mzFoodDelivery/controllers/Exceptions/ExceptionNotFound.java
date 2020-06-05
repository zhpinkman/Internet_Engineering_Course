package ir.ac.ut.ie.CA_08_mzFoodDelivery.controllers.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "entity not found")
public class ExceptionNotFound extends RuntimeException {
}