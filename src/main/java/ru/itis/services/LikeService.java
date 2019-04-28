package ru.itis.services;

import ru.itis.models.Like;
import ru.itis.models.Question;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    void addLike(Like like);
    void deleteLike(Like like);
    Optional<Like> getLikeById(Integer id);
    List<Like> getLikesByQuestion(Question question);
    List<Like> getLikesByUser(User user);

    boolean toggle(Question question, User currentUser);
}
