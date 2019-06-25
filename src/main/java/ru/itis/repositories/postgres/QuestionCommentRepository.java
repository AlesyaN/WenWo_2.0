package ru.itis.repositories.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.QuestionComment;

import java.util.List;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Integer> {
    List<QuestionComment> findAllByQuestion_Id(Integer questionId);

    @Query("from QuestionComment c where c.text like CONCAT('%',:text,'%')")
    List<QuestionComment> search(@Param("text") String text);
}
