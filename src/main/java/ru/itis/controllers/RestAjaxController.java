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
        User subscriptor = userService.getUserByLogin(request.getParameter("login")).orElseThrow(IllegalArgumentException::new);
        Integer currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
        User currentUser = userService.getUserById(currentUserId).orElseThrow(IllegalArgumentException::new);
        boolean followed = userService.toggleSubscription(subscriptor, currentUser);
        return ResponseEntity.ok(followed);
    }

    @PostMapping("/api/ask")
    public ResponseEntity<Object> ask(HttpServletRequest request, Authentication authentication) {
        User subscriptor = userService.getUserByLogin(request.getParameter("login")).orElseThrow(IllegalArgumentException::new);
        Integer currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
        User currentUser = userService.getUserById(currentUserId).orElseThrow(IllegalArgumentException::new);
        String questionText = request.getParameter("question");
        Question question = Question.builder()
                .sender(currentUser)
                .receiver(subscriptor)
                .text(questionText)
                .date(new Date())
                .build();
        questionService.addOrUpdateQuestion(question);
        int numOfUnansweredQuestions = questionService.getUserUnansweredQuestionsBySender(subscriptor, currentUser).size();
        return ResponseEntity.ok(numOfUnansweredQuestions);
    }

    @PostMapping("/api/answer")
    public ResponseEntity<Object> answer(HttpServletRequest request) {
        Question question = questionService.getQuestionById(Integer.parseInt(request.getParameter("id"))).orElseThrow(IllegalArgumentException::new);
        question.setAnswer(request.getParameter("answer"));
        questionService.addOrUpdateQuestion(question);
        return ResponseEntity.ok().build();
    }
}
