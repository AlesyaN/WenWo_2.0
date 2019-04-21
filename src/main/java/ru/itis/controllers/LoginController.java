package ru.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, ModelMap modelMap, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect:/profile";
        }
        if (request.getParameterMap().containsKey("error")) {
            modelMap.addAttribute("error", true);
        }
        return "login";
    }

}
