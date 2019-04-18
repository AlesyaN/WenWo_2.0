package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.models.Like;
import ru.itis.models.Message;
import ru.itis.models.Question;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.services.LikeService;
import ru.itis.services.MessageService;
import ru.itis.services.QuestionService;
import ru.itis.services.UserService;

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


    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String control() {
        Optional<User> userOptional = userService.authenticate("login", "5f4dcc3b5aa765d61d8327deb882cf99");
        return "page";
    }

}
