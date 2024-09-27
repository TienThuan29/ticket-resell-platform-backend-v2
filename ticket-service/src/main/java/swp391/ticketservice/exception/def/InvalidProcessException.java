package swp391.ticketservice.exception.def;

public class InvalidProcessException extends RuntimeException{
    public InvalidProcessException(String message) {
        super(message);
    }
}
