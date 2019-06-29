package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.forms.PhotoForm;
import ru.itis.models.Photo;
import ru.itis.models.User;
import ru.itis.services.AlbumService;
import ru.itis.services.PhotoService;
import ru.itis.services.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @Autowired
    UserService userService;

    @Autowired
    PhotoService photoService;

    @GetMapping("/albums/{album-id}")
    public String getAlbum(@PathVariable("album-id") Integer albumId, ModelMap modelMap, Authentication authentication) {
        modelMap.addAttribute("album", albumService.getAlbum(albumId).orElseThrow(IllegalArgumentException::new));
        Optional<User> currentUserOptional = userService.getCurrentUser(authentication);
        currentUserOptional.ifPresent(user -> modelMap.addAttribute("currentUserId", user.getId()));
        return "album";
    }

    @PostMapping("/albums/{album-id}")
    public String addPhoto(@PathVariable("album-id") Integer albumId, @Valid PhotoForm photoForm, BindingResult bindingResult, ModelMap modelMap, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("album", albumService.getAlbum(albumId).orElseThrow(IllegalArgumentException::new));
            Optional<User> currentUserOptional = userService.getCurrentUser(authentication);
            currentUserOptional.ifPresent(user -> modelMap.addAttribute("currentUserId", user.getId()));
            modelMap.addAttribute("error", true);
            return "album";
        }
        photoForm.setAlbum(albumService.getAlbum(albumId).orElseThrow(IllegalArgumentException::new));
        photoService.addPhoto(photoForm);
        return "redirect:/albums/" + albumId;
    }

    @PostMapping("albums/{album-id}/delete")
    public String deleteAlbum(@PathVariable("album-id") Integer albumId) {
        albumService.deleteAlbum(albumService.getAlbum(albumId).orElseThrow(IllegalArgumentException::new));
        return "redirect:/profile";
    }
}
