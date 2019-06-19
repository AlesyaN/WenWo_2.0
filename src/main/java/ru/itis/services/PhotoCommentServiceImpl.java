package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itis.models.Comment;
import ru.itis.models.PhotoComment;
import ru.itis.repositories.postgres.PhotoCommentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("photoCommentService")
public class PhotoCommentServiceImpl implements CommentService{

    @Autowired
    private PhotoCommentRepository commentRepository;

    @Override
    public void addComment(Comment comment) {
        commentRepository.save((PhotoComment)comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete((PhotoComment) comment);
    }

    @Override
    public Optional<Comment> getCommentById(Integer commentId) {
        Optional<PhotoComment> commentOptional = commentRepository.findById(commentId);
        return commentOptional.map(comment -> (Comment) comment);
    }

    @Override
    public List<Comment> searchComments(String text) {
        return commentRepository.search(text).stream().map(comment -> (Comment)comment).collect(Collectors.toList());
    }
}
