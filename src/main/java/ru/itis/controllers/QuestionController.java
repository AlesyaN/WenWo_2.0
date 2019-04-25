package ru.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;

@Controller
public class QuestionController {

    @GetMapping("/questions")
    public String getQuestionsPage(Authentication authentication, ModelMap modelMap) {
        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        modelMap.addAttribute("questions", currentUser.getUnansweredQuestions());
        return "questions";
    }
}
