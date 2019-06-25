package ru.itis.repositories.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Like;
import ru.itis.models.Question;
import ru.itis.models.QuestionLike;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Integer> {
    Optional<QuestionLike> findOneByQuestion_IdAndUser_Id(Integer questionId, Integer userId);
}
