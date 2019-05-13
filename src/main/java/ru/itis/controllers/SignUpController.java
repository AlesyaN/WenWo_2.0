package ru.itis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.forms.UserRegisterForm;
import ru.itis.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SignUpController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserRegisterForm form, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            List<String> errors = result
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
            modelMap.addAttribute("errors", errors);
            return "signUp";
        }
        userService.signUp(form);
        return "redirect:/profile";
    }
}
