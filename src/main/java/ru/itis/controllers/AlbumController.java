package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.models.User;
import ru.itis.services.AlbumService;
import ru.itis.services.UserService;

import java.util.Optional;

@Controller
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @Autowired
    UserService userService;

    @GetMapping("/albums/{album-id}")
    public String getAlbum(@PathVariable("album-id") Integer albumId, ModelMap modelMap, Authentication authentication) {
        modelMap.addAttribute("album", albumService.getAlbum(albumId).orElseThrow(IllegalArgumentException::new));
        Optional<User> currentUserOptional = userService.getCurrentUser(authentication);
        currentUserOptional.ifPresent(user -> modelMap.addAttribute("currentUserId", user.getId()));
        return "album";
    }
}
