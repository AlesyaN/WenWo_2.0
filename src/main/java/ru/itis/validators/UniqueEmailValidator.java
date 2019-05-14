package ru.itis.validators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

   @Autowired
   private UserService userService;

   public void initialize(UniqueEmail constraint) {
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      return userService.emailIsUnique(email);
   }
}
