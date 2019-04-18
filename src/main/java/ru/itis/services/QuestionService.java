package ru.itis.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Question;
import ru.itis.models.User;
import ru.itis.repositories.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question getQuestionById(int id) {
        return questionRepository.findOne(id);
    }

    public List<Question> getAllUserQuestions(User user){
        return questionRepository.findAllByReceiver_Id(user.getId());
    }

    public List<Question> getUserAnsweredQuestions(User user) {
        return questionRepository.findAllByReceiver_IdAndAnswerIsNotNull(user.getId());
    }

    public List<Question> getUserUnansweredQuestions(User user) {
        return questionRepository.findAllByReceiver_IdAndAnswerIsNull(user.getId());
    }
    public List<Question> getAllUserQuestionsBySender(User receiver, User sender){
        return questionRepository.findAllByReceiver_IdAndSender_Id(receiver.getId(), sender.getId());
    }
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }
    public void deleteQuestion(Question question){
        questionRepository.delete(question);
    }
}
