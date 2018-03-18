package residentEvilApp.validator;

import residentEvilApp.annotation.ResidentEvilDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by George-Lenovo on 17/03/2018.
 */
public class ResidentEvilDateValidator implements ConstraintValidator<ResidentEvilDate, LocalDate> {
    @Override
    public void initialize(ResidentEvilDate residentEvilDate) {

    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) return false;
        return date.isBefore(LocalDate.now());
    }
}
