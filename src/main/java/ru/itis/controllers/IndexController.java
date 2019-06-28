package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.services.InfoService;

@Controller
public class IndexController {

    @Autowired
    InfoService infoService;

    @GetMapping("/")
    public String getIndexPage(ModelMap modelMap) {
        modelMap.addAttribute("info", infoService.getInfo());
        return "index";
    }
}
