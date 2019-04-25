package ru.itis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Question;
import ru.itis.models.User;
import ru.itis.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getQuestionById(Integer id) {
        return Optional.ofNullable(questionRepository.findOne(id));
    }

    public List<Question> getAllUserQuestions(User user){
        return questionRepository.findAllByReceiver_IdOrderByDate(user.getId());
    }

    public List<Question> getUserAnsweredQuestions(User user) {
        return questionRepository.findAllByReceiver_IdAndAnswerIsNotNullOrderByDate(user.getId());
    }

    public List<Question> getUserUnansweredQuestions(User user) {
        return questionRepository.findAllByReceiver_IdAndAnswerIsNullOrderByDate(user.getId());
    }
    public List<Question> getAllUserQuestionsBySender(User receiver, User sender){
        return questionRepository.findAllByReceiver_IdAndSender_IdOrderByDate(receiver.getId(), sender.getId());
    }
    public void addOrUpdateQuestion(Question question) {
        questionRepository.save(question);
    }
    public void deleteQuestion(Question question){
        questionRepository.delete(question);
    }

    @Override
    public List<Question> getUsersFeed(User currentUser) {
        return questionRepository.findFeedByUser_Id(currentUser.getId());
    }

    @Override
    public List<Question> getUserUnansweredQuestionsBySender(User user, User currentUser) {
        return questionRepository.findAllByReceiver_IdAndSender_IdAndAnswerIsNullOrderByDate(user.getId(), currentUser.getId());
    }
}
