package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itis.models.Comment;
import ru.itis.models.QuestionComment;
import ru.itis.models.Question;
import ru.itis.repositories.postgres.QuestionCommentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("questionCommentService")
public class QuestionCommentServiceImpl implements CommentService {

    @Autowired
    private QuestionCommentRepository commentRepository;

    @Override
    public void addComment(Comment comment) {
        commentRepository.save((QuestionComment)comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete((QuestionComment) comment);
    }

    public List<QuestionComment> findAllByQuestion(Question question) {
        return commentRepository.findAllByQuestion_Id(question.getId());
    }

    @Override
    public Optional<Comment> getCommentById(Integer commentId) {
        Optional<QuestionComment> commentOptional = commentRepository.findById(commentId);
        return commentOptional.map(questionComment -> (Comment) questionComment);
    }

    @Override
    public List<Comment> searchComments(String text) {
        return commentRepository.search(text).stream().map(comment -> (Comment)comment).collect(Collectors.toList());
    }
}
