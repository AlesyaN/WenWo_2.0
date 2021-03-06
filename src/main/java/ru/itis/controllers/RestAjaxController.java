package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.forms.CommentForm;
import ru.itis.models.*;
import ru.itis.services.*;
import ru.itis.transfer.AlbumDto;
import ru.itis.transfer.CommentDto;
import ru.itis.transfer.PhotoDto;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class RestAjaxController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    LikeService likeService;

    @Autowired
    @Qualifier("questionCommentService")
    CommentService questionCommentService;

    @Autowired
    @Qualifier("photoCommentService")
    CommentService photoCommentService;

    @Autowired
    AlbumService albumService;

    @Autowired
    PhotoService photoService;

    @PostMapping("/api/follow")
    public ResponseEntity<Object> follow(@RequestParam("login") String login, Authentication authentication) {
        User subscriptor = userService.getUserByLogin(login).orElseThrow(IllegalArgumentException::new);
        User currentUser = userService.getCurrentUser(authentication).orElse(null);
        boolean followed = userService.toggleSubscription(subscriptor, currentUser);
        return ResponseEntity.ok(followed);
    }

    @PostMapping("/api/ask")
    public ResponseEntity<Object> ask(@RequestParam("login") String login, @RequestParam("question") String questionText, @RequestParam("anonymous") boolean anonymous, Authentication authentication) {
        User subscriptor = userService.getUserByLogin(login).orElseThrow(IllegalArgumentException::new);
        User currentUser = userService.getCurrentUser(authentication).orElse(null);
        Question question = Question.builder()
                .anonymous(anonymous)
                .sender(currentUser)
                .receiver(subscriptor)
                .text(questionText)
                .date(new Date())
                .build();
        questionService.addOrUpdateQuestion(question);
        return ResponseEntity.ok(question.getId());
    }

    @PostMapping("/api/answer")
    public ResponseEntity<Object> answer(@RequestParam("id") Integer id, @RequestParam("answer") String answer) {
        Question question = questionService.getQuestionById(id).orElseThrow(IllegalArgumentException::new);
        question.setAnswer(answer);
        questionService.addOrUpdateQuestion(question);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/like")
    public ResponseEntity<Object> like(@RequestParam("id") Integer id,
                                       @RequestParam("type") String type, Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication).orElse(null);
        boolean isLike = false;
        if (type.equals("question")) {
            Question question = questionService.getQuestionById(id).orElseThrow(IllegalArgumentException::new);
            isLike = likeService.toggle(question, currentUser);
        } else if (type.equals("photo")) {
            Photo photo = photoService.getPhotoById(id).orElseThrow(IllegalArgumentException::new);
            isLike = likeService.toggle(photo, currentUser);
        }
        return ResponseEntity.ok(isLike);
    }

    @PostMapping("/api/deleteQuestion")
    public ResponseEntity<Object> deleteQuestion(@RequestParam("id") Integer id) {
        Question question = questionService.getQuestionById(id).orElseThrow(IllegalArgumentException::new);
        questionService.deleteQuestion(question);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/editAnswer")
    public ResponseEntity<Object> editAnswer(@RequestParam("questionId") Integer questionId, @RequestParam("answer") String answer) {
        Question question = questionService.getQuestionById(questionId).orElseThrow(IllegalArgumentException::new);
        question.setAnswer(answer);
        questionService.addOrUpdateQuestion(question);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/addComment")
    public ResponseEntity<Object> addComment(@RequestBody @Valid CommentForm commentForm, BindingResult result, Authentication authentication) {
        if (result.hasErrors()) return ResponseEntity.badRequest().build();
        User author = userService.getCurrentUser(authentication).orElse(null);
        if (commentForm.getType().equals("question")) {
            Question question = questionService.getQuestionById(commentForm.getPostId()).orElseThrow(IllegalArgumentException::new);
            QuestionComment comment = QuestionComment.builder()
                    .author(author)
                    .question(question)
                    .date(new Date())
                    .text(commentForm.getText())
                    .build();
            questionCommentService.addComment(comment);
            return ResponseEntity.ok(CommentDto.from(comment));
        } else if (commentForm.getType().equals("photo")) {
            Photo photo = photoService.getPhoto(commentForm.getPostId()).orElseThrow(IllegalArgumentException::new);
            PhotoComment comment = PhotoComment.builder()
                    .author(author)
                    .photo(photo)
                    .date(new Date())
                    .text(commentForm.getText())
                    .build();
            photoCommentService.addComment(comment);
            return ResponseEntity.ok(CommentDto.from(comment));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/api/deleteComment")
    public ResponseEntity<Object> deleteComment(@RequestParam("commentId") Integer commentId, @RequestParam("type") String type) {
        if (type.equals("question")) {
            Comment comment = questionCommentService.getCommentById(commentId).orElseThrow(IllegalArgumentException::new);
            questionCommentService.deleteComment(comment);
            return ResponseEntity.ok().build();
        } else if (type.equals("photo")) {
            Comment comment = photoCommentService.getCommentById(commentId).orElseThrow(IllegalArgumentException::new);
            photoCommentService.deleteComment(comment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/api/userExists")
    public ResponseEntity<Object> userExists(@RequestParam("login") String login) {
        if (!userService.loginIsUnique(login)) {
            return ResponseEntity.ok(login);
        } else {
            return ResponseEntity.ok("");
        }
    }

    @PostMapping("/api/addAlbum")
    public ResponseEntity<Object> addAlbum(@RequestParam("name")String name, Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication).orElseThrow(IllegalAccessError::new);
        Album album = Album.builder()
                .name(name)
                .owner(currentUser)
                .build();
        albumService.addAlbum(album);
        return ResponseEntity.ok(AlbumDto.from(album));
    }

    @PostMapping("/api/editAlbumName")
    public ResponseEntity<Object> editAlbumName(@RequestParam("album-id")Integer albumId,
                                                @RequestParam("new-name") String newName) {
        albumService.editAlbumName(albumId, newName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/deletePhoto")
    public ResponseEntity<Object> deletePhoto(@RequestParam("photo-id") Integer photoId) {
        photoService.deletePhoto(photoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/editPhotoDescription")
    public ResponseEntity<Object> newPhotoDescription(@RequestParam("photo-id")Integer photoId,
                                                      @RequestParam("new-description") String newDescription) {
        photoService.editPhotoDescription(photoId, newDescription);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/getPhotosWithGPS")
    public ResponseEntity<Object> getPhotosWithGPS(@RequestParam("login")String login) {
        return ResponseEntity.ok(photoService.getPhotosWithGPS(login).stream().map(PhotoDto::from));
    }
}
