package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.models.*;
import ru.itis.repositories.UserRepository;
import ru.itis.services.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class TestController {

    @Autowired
    LikeService likeService;

    @Autowired
    MessageService messageService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @Autowired
    SubscriptionService subscriptionService;


    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String control() {
        for (Subscription s: subscriptionService.getSubscriptorsByUser(userService.getUserById(1).get())) {
            System.out.println(s);
        }
        return "page";
    }

}
