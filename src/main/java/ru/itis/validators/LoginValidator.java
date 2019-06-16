package ru.itis.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator implements ConstraintValidator<Login, String> {

   @Autowired
   UserService userService;

   public void initialize(Login constraint) {
   }

   public boolean isValid(String login, ConstraintValidatorContext context) {
      return isUnique(login, context) && matchesPattern(login, context);
   }

   private boolean matchesPattern(String login, ConstraintValidatorContext context) {
      boolean valid = true;
      String pattern = "^[a-z0-9_-]{3,16}$";
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(login);
      if (!m.matches()) {
         valid = false;
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate("{Login.matchesPattern.message}").addConstraintViolation();
      }
      return valid;
   }

   public boolean isUnique(String login, ConstraintValidatorContext context) {
      boolean valid = true;
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken) && ((UserDetailsImpl)auth.getPrincipal()).getUser().getLogin().equals(login)) {
         return true;
      }
      if (!userService.loginIsUnique(login)) {
         valid = false;
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate("{Login.isUnique.message}").addConstraintViolation();
      }
      return valid;
   }


}
