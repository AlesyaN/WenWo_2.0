package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.models.User;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class RestAjaxController {

    @Autowired
    UserService userService;

    @PostMapping("/api/follow")
    public ResponseEntity<Object> follow(HttpServletRequest request, Authentication authentication) {
        Optional<User> subscriptorCandidate = userService.getUserByLogin(request.getParameter("login"));
        User currentUser = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        if (subscriptorCandidate.isPresent() ) {
            boolean followed = userService.toggleSubscription(subscriptorCandidate.get(),currentUser);
            return ResponseEntity.ok(followed);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
