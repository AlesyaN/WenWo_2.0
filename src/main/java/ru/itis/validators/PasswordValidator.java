package ru.itis.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    public void initialize(Password constraint) {
    }

    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean valid = true;
        if (password.equals("")) return true;
        String pattern = "^[a-z0-9_-]{6,18}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        if (!m.matches()) {
            valid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{Password.matchesPattern.message}").addConstraintViolation();
        }
        return valid;
    }
}
