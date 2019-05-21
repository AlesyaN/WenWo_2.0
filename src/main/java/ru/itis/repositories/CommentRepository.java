package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByQuestion_Id(Integer questionId);

    @Query("from Comment c where c.text like CONCAT('%',:text,'%')")
    List<Comment> search(@Param("text") String text);
}
