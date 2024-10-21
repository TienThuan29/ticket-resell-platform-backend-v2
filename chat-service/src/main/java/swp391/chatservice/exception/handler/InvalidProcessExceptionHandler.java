package swp391.chatservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import swp391.ticketservice.exception.def.ErrorResponse;
import swp391.ticketservice.exception.def.InvalidProcessException;

@RestControllerAdvice
public class InvalidProcessExceptionHandler {
    @ExceptionHandler(InvalidProcessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(InvalidProcessException exception){
        return new ErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
    }
}
