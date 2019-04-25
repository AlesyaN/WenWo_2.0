package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.forms.UserEditForm;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.UserService;

import static ru.itis.transfer.UserEditDto.from;

@Controller
public class EditProfileController {

    @Autowired
    UserService userService;

    @GetMapping("/editProfile")
    public String getEditPage(Authentication authentication, ModelMap modelMap) {
        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        modelMap.addAttribute("user", from(currentUser));
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String edit(UserEditForm form, Authentication authentication, ModelMap modelMap) {
        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        userService.editProfile(form, currentUser);
        modelMap.addAttribute("user", currentUser);
        return "redirect:/profile";
    }
}
