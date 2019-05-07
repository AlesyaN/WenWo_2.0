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
        Integer currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
        User user = userService.getUserById(currentUserId).orElseThrow(IllegalArgumentException::new);
        UserDto userDto = from(user);
        modelMap.addAttribute("user", userDto);
        return "myprofile";
    }

    @GetMapping("/profile/{id}")
    public String getProfilePage(@PathVariable("id") Integer id, ModelMap modelMap, Authentication authentication) {
        Optional<User> userCandidate = userService.getUserById(id);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            Integer currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
            User currentUser = userService.getUserById(currentUserId).orElseThrow(IllegalArgumentException::new);
            if (user.getId().equals(currentUser.getId())) return "redirect:/profile";
            List<Question> questions = questionService.getUserUnansweredQuestionsBySender(user, currentUser);
            modelMap.addAttribute("unansweredQuestions", questions);
            modelMap.addAttribute("user", from(user));
            modelMap.addAttribute("currentUserId", currentUser.getId());
        }
        return "profile";
    }

}
