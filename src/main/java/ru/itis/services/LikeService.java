package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Like;
import ru.itis.models.Question;
import ru.itis.models.User;
import ru.itis.repositories.LikeRepository;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository ;

    public void addLike(Like like) {
        likeRepository.save(like);
    }

    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }

    public Like getLikeById(Integer id) {
        return likeRepository.findOne(id);
    }

    public List<Like> getLikesByQuestion(Question question) {
        return likeRepository.findAllByQuestion_Id(question.getId());
    }

    public List<Like> getLikesByUser(User user) {
        return likeRepository.findAllByUser_Id(user.getId());
    }
}
