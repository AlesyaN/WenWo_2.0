package ru.itis.validators;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<File, MultipartFile> {
   public void initialize(File constraint) {
   }

   @Override
   public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
      boolean valid = true;
      if (!multipartFile.getContentType().toLowerCase().equals("image/jpeg") &&
              !multipartFile.getContentType().toLowerCase().equals("image/png") &&
              !multipartFile.getContentType().toLowerCase().equals("image/jpg")) {
          valid = false;
          constraintValidatorContext.disableDefaultConstraintViolation();
          constraintValidatorContext.buildConstraintViolationWithTemplate("File should be jpg, jpeg or png").addConstraintViolation();
      }

      return valid;

   }



}
