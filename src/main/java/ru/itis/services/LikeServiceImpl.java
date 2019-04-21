package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Like;
import ru.itis.models.Question;
import ru.itis.models.User;
import ru.itis.repositories.LikeRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService{
    @Autowired
    private LikeRepository likeRepository ;

    public void addLike(Like like) {
        likeRepository.save(like);
    }

    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }

    public Optional<Like> getLikeById(Integer id) {
        return Optional.ofNullable(likeRepository.findOne(id));
    }

    public List<Like> getLikesByQuestion(Question question) {
        return likeRepository.findAllByQuestion_Id(question.getId());
    }

    public List<Like> getLikesByUser(User user) {
        return likeRepository.findAllByUser_Id(user.getId());
    }
}
