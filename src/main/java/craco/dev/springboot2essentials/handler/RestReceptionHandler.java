package craco.dev.springboot2essentials.handler;

import craco.dev.springboot2essentials.exception.BadRequestException;
import craco.dev.springboot2essentials.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestReceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        var newBadRequestException = new BadRequestExceptionDetails(
                "Bad Request Exceptions",
                HttpStatus.BAD_REQUEST.value(),
                badRequestException.getMessage(),
                badRequestException.getClass().getName(),
                LocalDateTime.now());

        return new ResponseEntity<>(newBadRequestException, HttpStatus.BAD_REQUEST);
    }
}
