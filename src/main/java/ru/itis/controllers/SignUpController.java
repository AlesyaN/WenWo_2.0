package ru.itis.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.forms.UserRegisterForm;
import ru.itis.services.UserServiceImpl;

@Controller
public class SignUpController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserRegisterForm form, ModelMap modelMap) {
        boolean registered = userService.signUp(form);
        if (registered) {
            return "redirect:/profile";
        } else {
            modelMap.addAttribute("error", true);
            return "signUp";
        }
    }
}
