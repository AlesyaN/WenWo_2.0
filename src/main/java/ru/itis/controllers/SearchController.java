package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.models.User;
import ru.itis.services.CommentService;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @GetMapping("/search")
    public String getSearchPage() {
        return "search";
    }

    @GetMapping(value = "/search", params = "search-text")
    public String searchText(@RequestParam("search-text") String text, ModelMap modelMap, Authentication authentication) {
        if (!text.equals("")) {
            modelMap.addAttribute("users", userService.searchUsers(text));
            modelMap.addAttribute("questions", questionService.searchQuestions(text));
            Optional<User> userOptional = userService.getCurrentUser(authentication);
            userOptional.ifPresent(user -> modelMap.addAttribute("currentUserId", user.getId()));
        }
        return "search";
    }

    @GetMapping(value = "/search", params = "hashtag")
    public String searchHashtag(@RequestParam("hashtag") String text, ModelMap modelMap, Authentication authentication) {
        modelMap.addAttribute("questions", questionService.searchQuestions(text));
        Optional<User> userOptional = userService.getCurrentUser(authentication);
        userOptional.ifPresent(user -> modelMap.addAttribute("currentUserId", user.getId()));
        return "search";
    }
}
