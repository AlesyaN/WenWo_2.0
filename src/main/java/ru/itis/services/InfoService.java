package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Info;
import ru.itis.repositories.mongo.InfoRepository;
import ru.itis.repositories.postgres.QuestionRepository;
import ru.itis.repositories.postgres.UserRepository;

import java.time.LocalDateTime;

@Service
public class InfoService {

    @Autowired
    InfoRepository infoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    public Info getInfo() {
        return infoRepository.findTopByOrderByUpdatedDesc();
    }

    public void update() {
        Long numOfUsers = userRepository.count();
        Long numOfQuestions = questionRepository.count();
        Long numOfAnonymousQuestions = questionRepository.countAllByAnonymousIsTrue();
        Long numOfAnswers = questionRepository.countAllByAnswerIsNotNull();
        Info info = Info.builder()
                .users(numOfUsers)
                .questions(numOfQuestions)
                .anonymousQuestions(numOfAnonymousQuestions)
                .answers(numOfAnswers)
                .updated(LocalDateTime.now())
                .build();
        infoRepository.save(info);
    }

}
