package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.models.Question;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;
import ru.itis.transfer.UserDto;

import java.util.List;
import java.util.Optional;

import static ru.itis.transfer.UserDto.from;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile")
    public String getProfilePage(ModelMap modelMap, Authentication authentication) {
        User user = userService.getCurrentUser(authentication).orElseThrow(IllegalAccessError::new);
        return "redirect:/profile/" + user.getLogin();
    }

    @GetMapping("/profile/{login}")
    public String getProfilePage(@PathVariable("login") String login, ModelMap modelMap, Authentication authentication) {
        Optional<User> userCandidate = userService.getUserByLogin(login);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            Optional<User> currentUserOptional = userService.getCurrentUser(authentication);
            modelMap.addAttribute("user", from(user));
            if (currentUserOptional.isPresent()) {
                User currentUser = currentUserOptional.get();
                if (user.getId().equals(currentUserOptional.get().getId())) return "myprofile";
                List<Question> questions = questionService.getUserUnansweredQuestionsBySender(user, currentUser);
                modelMap.addAttribute("unansweredQuestions", questions);
                modelMap.addAttribute("currentUserId", currentUser.getId());
            }
        }
        return "profile";
    }

}
