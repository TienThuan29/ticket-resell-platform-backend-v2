package swp391.userservice.exception.def;

import org.springframework.validation.BindingResult;

/**
 * Author: Nguyen Tien Thuan
 */
public class BindException extends org.springframework.validation.BindException {
    public BindException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
