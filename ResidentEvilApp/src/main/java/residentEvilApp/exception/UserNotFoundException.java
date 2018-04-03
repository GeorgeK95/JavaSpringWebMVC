package residentEvilApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by George-Lenovo on 30/03/2018.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User with the given name was not found.")
public class UserNotFoundException extends RuntimeException {
}
