package ru.itis.repositories.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Question;
import ru.itis.models.User;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAllByReceiver_Id(Integer id);

    List<Question> findAllByReceiver_IdAndAnswerIsNull(Integer id);

    List<Question> findAllByReceiver_IdAndAnswerIsNotNull(Integer id);

    List<Question> findAllByReceiver_IdAndSender_Id(Integer receiverId, Integer senderId);

    @Query("select q from User u join u.followings f join f.questions q where u.id = :id and q.answer is not null order by q.date desc")
    List<Question> findFeedByUser_Id(@Param("id") Integer id);

    List<Question> findAllByReceiver_IdAndSender_IdAndAnswerIsNull(Integer receiverId, Integer senderId);

    @Query("from Question q where q.answer is not null and (q.text like CONCAT('%',:text,'%') or q.answer like CONCAT('%',:text,'%'))")
    List<Question> search(@Param("text") String text);

    Long countAllByAnonymousIsTrue();

    Long countAllByAnswerIsNotNull();
}
