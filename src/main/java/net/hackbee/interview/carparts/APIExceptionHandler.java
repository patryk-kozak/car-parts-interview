package net.hackbee.interview.carparts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class APIExceptionHandler {

    // Set on response and internally in object.
    // Internally probably not needed, useful for GUI clients.
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PartNotFoundException.class)
    public ApiError handlePartNotFoundException(PartNotFoundException exception) {
        return new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

}
