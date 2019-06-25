package ru.itis.repositories.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.PhotoLike;
import ru.itis.models.QuestionLike;

import java.util.Optional;

@Repository
public interface PhotoLikeRepository extends JpaRepository<PhotoLike, Integer> {
    Optional<PhotoLike> findOneByPhoto_IdAndUser_Id(Integer photoId, Integer userId);
}
