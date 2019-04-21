package ru.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.transfer.UserDto;

import java.util.Arrays;

import static ru.itis.transfer.UserDto.from;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String getProfilePage(ModelMap modelMap, Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        UserDto user = from(details.getUser());
        modelMap.addAttribute("user", user);
        return "profile";
    }

}
