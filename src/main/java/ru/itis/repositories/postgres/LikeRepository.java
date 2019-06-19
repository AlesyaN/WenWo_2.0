package ru.itis.repositories.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Like;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    List<Like> findAllByQuestion_Id(Integer questionId);
    List<Like> findAllByUser_Id(Integer id);
    Optional<Like> findOneByQuestion_IdAndUser_Id(Integer questionId, Integer userId);
}
