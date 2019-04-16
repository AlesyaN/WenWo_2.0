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
import ru.itis.services.LikeService;
import ru.itis.services.MessageService;

import java.util.Date;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    LikeService likeService;

    @Autowired
    MessageService messageService;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String control() {
        User user1 = User.builder().id(1).build();
        User user2 = User.builder().id(2).build();
        Question question = Question.builder().id(2).build();
        Message message = messageService.getMessageById(1);
        System.out.println(messageService.deleteMessage(message, user1));
        return "page";
    }

}
