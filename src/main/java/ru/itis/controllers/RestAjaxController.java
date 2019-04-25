package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.models.Question;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@RestController
public class RestAjaxController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @PostMapping("/api/follow")
    public ResponseEntity<Object> follow(HttpServletRequest request, Authentication authentication) {
        Optional<User> subscriptorCandidate = userService.getUserByLogin(request.getParameter("login"));
        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        if (subscriptorCandidate.isPresent()) {
            boolean followed = userService.toggleSubscription(subscriptorCandidate.get(),currentUser);
            return ResponseEntity.ok(followed);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/api/ask")
    public ResponseEntity<Object> ask(HttpServletRequest request, Authentication authentication) {
        Optional<User> userCandidate = userService.getUserByLogin(request.getParameter("login"));
        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        String questionText = request.getParameter("question");
        if (userCandidate.isPresent()) {
            Question question = Question.builder()
                                        .sender(currentUser)
                                        .receiver(userCandidate.get())
                                        .text(questionText)
                                        .date(new Date())
                                        .build();
            questionService.addOrUpdateQuestion(question);
            int numOfUnansweredQuestions = questionService.getUserUnansweredQuestionsBySender(userCandidate.get(), currentUser).size();
            return ResponseEntity.ok(numOfUnansweredQuestions);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
