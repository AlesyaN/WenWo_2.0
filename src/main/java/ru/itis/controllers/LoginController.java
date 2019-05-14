package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.models.Comment;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.CommentService;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, ModelMap modelMap, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect:/profile/" + ((UserDetailsImpl)authentication.getPrincipal()).getUser().getId();
        }
        if (request.getParameterMap().containsKey("error")) {
            modelMap.addAttribute("error", true);
        }
        return "login";
    }

}
