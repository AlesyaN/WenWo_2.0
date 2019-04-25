package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Question;
import ru.itis.models.User;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByReceiver_IdOrderByDate(Integer id);

    List<Question> findAllByReceiver_IdAndAnswerIsNullOrderByDate(Integer id);

    List<Question> findAllByReceiver_IdAndAnswerIsNotNullOrderByDate(Integer id);

    List<Question> findAllByReceiver_IdAndSender_IdOrderByDate(Integer receiverId, Integer senderId);

    @Query("select q from User u join u.followings f join f.questions q where u.id = :id and q.answer is not null order by q.date desc")
    List<Question> findFeedByUser_Id(@Param("id") Integer id);

    List<Question> findAllByReceiver_IdAndSender_IdAndAnswerIsNullOrderByDate(Integer receiverId, Integer senderId);

}
