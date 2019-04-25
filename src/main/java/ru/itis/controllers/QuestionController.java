package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.UserService;

import java.util.Optional;

@Controller
public class QuestionController {

    @Autowired
    UserService userService;

    @GetMapping("/questions")
    public String getQuestionsPage(Authentication authentication, ModelMap modelMap) {
        Integer currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
        Optional<User> userCandidate = userService.getUserById(currentUserId);
        if (userCandidate.isPresent()) {
            User currentUser = userCandidate.get();
            modelMap.addAttribute("questions", currentUser.getUnansweredQuestions());
        }
        return "questions";
    }
}
