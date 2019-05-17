package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.services.UserService;

@Controller
public class SearchController {

    @Autowired
    UserService userService;

    @GetMapping("/search")
    public String search(@RequestParam("search-text") String text, ModelMap modelMap) {
        modelMap.addAttribute("result", userService.search(text));
        return "search";
    }
}
