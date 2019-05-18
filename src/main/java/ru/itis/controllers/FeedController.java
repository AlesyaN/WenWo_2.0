package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.models.Question;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class FeedController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @GetMapping("/feed")
    public String getFeed(ModelMap modelMap, Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication).orElseThrow(IllegalAccessError::new);
        List<Question> questions = questionService.getUsersFeed(currentUser);
        modelMap.addAttribute("feed", questions);
        modelMap.addAttribute("currentUserId", currentUser.getId());
        return "feed";
    }
}
