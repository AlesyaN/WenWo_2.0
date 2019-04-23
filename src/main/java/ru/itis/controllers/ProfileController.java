package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.UserService;
import ru.itis.transfer.UserDto;

import java.util.Optional;

import static ru.itis.transfer.UserDto.from;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String getProfilePage(ModelMap modelMap, Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        UserDto user = from(details.getUser());
        modelMap.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/{id}")
    public String getProfilePage(@PathVariable("id") Integer id, ModelMap modelMap) {
        Optional<User> userCandidate = userService.getUserById(id);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            modelMap.addAttribute("user", from(user));
        }
        return "profile";
    }

}
