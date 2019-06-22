package ru.itis.services;

import ru.itis.models.Comment;
import ru.itis.models.QuestionComment;
import ru.itis.models.Question;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void addComment(Comment comment);
    void deleteComment(Comment comment);

    Optional<Comment> getCommentById(Integer commentId);

}

