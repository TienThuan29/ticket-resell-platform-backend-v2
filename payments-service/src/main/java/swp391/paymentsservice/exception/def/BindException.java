package swp391.paymentsservice.exception.def;

import org.springframework.validation.BindingResult;

public class BindException extends org.springframework.validation.BindException {
    public BindException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
