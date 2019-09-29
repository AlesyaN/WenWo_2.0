package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.forms.UserEditForm;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.UserService;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.transfer.UserEditDto.from;

@Controller
public class EditProfileController {

    @Autowired
    UserService userService;

    @GetMapping("/editProfile")
    public String getEditPage(Authentication authentication, ModelMap modelMap) {
        User currentUser = userService.getCurrentUser(authentication).orElseThrow(IllegalAccessError::new);
        modelMap.addAttribute("user", from(currentUser));
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String edit(@Valid UserEditForm form, BindingResult result, Authentication authentication, ModelMap modelMap) {
        User currentUser = userService.getCurrentUser(authentication).orElseThrow(IllegalAccessError::new);
        modelMap.addAttribute("user", currentUser);
        if (result.hasErrors()) {
            List<String> errors = result
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
            modelMap.addAttribute("errors", errors);
            return "editProfile";
        }

        if (userService.editProfile(form, currentUser)) {
            return "redirect:/profile";
        } else {
            List<String> errors = new ArrayList<>();
            errors.add("Old password is wrong");
            modelMap.addAttribute("errors", errors);
            return "editProfile";
        }
    }
}
