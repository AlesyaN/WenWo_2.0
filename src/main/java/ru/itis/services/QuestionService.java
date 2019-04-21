package ru.itis.services;

import ru.itis.models.Question;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Optional<Question> getQuestionById(Integer id);
    List<Question> getAllUserQuestions(User user);
    List<Question> getUserAnsweredQuestions(User user);
    List<Question> getUserUnansweredQuestions(User user);
    List<Question> getAllUserQuestionsBySender(User receiver, User sender);
    void addOrUpdateQuestion(Question question);
    void deleteQuestion(Question question);

}
