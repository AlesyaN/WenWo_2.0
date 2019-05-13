package ru.itis.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    String message() default "{Login.default.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
