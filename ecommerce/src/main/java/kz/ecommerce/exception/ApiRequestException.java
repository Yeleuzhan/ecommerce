package kz.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
