package ru.itis.services;

import ru.itis.models.Comment;
import ru.itis.models.Question;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);
    void deleteComment(Comment comment);
    List<Comment> findAllByQuestion(Question question);
}

