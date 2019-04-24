package ru.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;

import static ru.itis.transfer.UserDto.from;

@Controller
public class EditProfileController {

    @GetMapping("/editProfile")
    public String getEditPage(Authentication authentication, ModelMap modelMap) {
        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        modelMap.addAttribute("user", from(currentUser));
        return "editProfile";
    }
}
