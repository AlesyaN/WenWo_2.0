package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    List<Like> findAllByQuestion_Id(Integer questionId);
    List<Like> findAllByUser_Id(Integer id);
}
