package ru.itis.services;

import ru.itis.models.Comment;
import ru.itis.models.Question;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    void addComment(Comment comment);
    void deleteComment(Comment comment);
    List<Comment> findAllByQuestion(Question question);

    Optional<Comment> getCommentById(Integer commentId);
}

