package swp391.ticketservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import swp391.ticketservice.exception.def.ErrorResponse;
import swp391.ticketservice.exception.def.NotFoundException;

@RestControllerAdvice(name = "ticketServiceNotFoundExceptionHandler")
public class NotFoundExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handle(NotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

}
