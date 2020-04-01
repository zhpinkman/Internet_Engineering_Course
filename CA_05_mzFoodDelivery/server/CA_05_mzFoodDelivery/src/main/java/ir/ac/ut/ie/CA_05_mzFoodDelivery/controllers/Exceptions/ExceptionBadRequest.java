package ir.ac.ut.ie.CA_05_mzFoodDelivery.controllers.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad Request!")
public class ExceptionBadRequest extends RuntimeException {
}