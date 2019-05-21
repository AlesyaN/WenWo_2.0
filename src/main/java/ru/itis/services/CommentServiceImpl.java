package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Comment;
import ru.itis.models.Question;
import ru.itis.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> findAllByQuestion(Question question) {
        return commentRepository.findAllByQuestion_Id(question.getId());
    }

    @Override
    public Optional<Comment> getCommentById(Integer commentId) {
        return Optional.ofNullable(commentRepository.findOne(commentId));
    }

    @Override
    public List<Comment> searchComments(String text) {
        return commentRepository.search(text);
    }
}
