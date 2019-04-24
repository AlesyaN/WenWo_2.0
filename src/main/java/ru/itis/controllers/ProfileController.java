package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        UserDto userDto = from(user, user);
        modelMap.addAttribute("user", userDto);
        return "myprofile";
    }

    @GetMapping("/profile/{id}")
    public String getProfilePage(@PathVariable("id") Integer id, ModelMap modelMap, Authentication authentication) {
        Optional<User> userCandidate = userService.getUserById(id);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
            if (user.getId().equals(currentUser.getId())) {
                modelMap.addAttribute("user", from(currentUser));
                return "myprofile";
            }
            modelMap.addAttribute("user", from(user, currentUser));
        }
        return "profile";
    }

}
