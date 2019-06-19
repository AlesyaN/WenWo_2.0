package ru.itis.repositories.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.PhotoComment;
import ru.itis.models.QuestionComment;

import java.util.List;

public interface PhotoCommentRepository extends JpaRepository<PhotoComment, Integer> {
    @Query("from PhotoComment c where c.text like CONCAT('%',:text,'%')")
    List<QuestionComment> search(@Param("text") String text);
}
