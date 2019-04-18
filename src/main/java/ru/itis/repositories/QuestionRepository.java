package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Question;

import javax.jnlp.IntegrationService;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByReceiver_Id(Integer id);

    List<Question> findAllByReceiver_IdAndAnswerIsNull(Integer id);

    List<Question> findAllByReceiver_IdAndAnswerIsNotNull(Integer id);

    List<Question> findAllByReceiver_IdAndSender_Id(Integer receiverId, Integer senderId);

}
