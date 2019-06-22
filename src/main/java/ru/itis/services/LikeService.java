package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.*;
import ru.itis.repositories.postgres.PhotoLikeRepository;
import ru.itis.repositories.postgres.QuestionLikeRepository;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private QuestionLikeRepository questionLikeRepository ;

    @Autowired
    private PhotoLikeRepository photoLikeRepository;

    public boolean toggle(Question question, User currentUser) {
        Optional<QuestionLike> like = questionLikeRepository.findOneByQuestion_IdAndUser_Id(question.getId(), currentUser.getId());
        if (like.isPresent()) {
            questionLikeRepository.delete(like.get());
            return false;
        } else {
            QuestionLike newLike = QuestionLike.builder()
                                .question(question)
                                .user(currentUser)
                                .build();
            questionLikeRepository.save(newLike);
            return true;
        }
    }

    public boolean toggle(Photo photo, User currentUser) {
        Optional<PhotoLike> like = photoLikeRepository.findOneByPhoto_IdAndUser_Id(photo.getId(), currentUser.getId());
        if (like.isPresent()) {
            photoLikeRepository.delete(like.get());
            return false;
        } else {
            PhotoLike newLike = PhotoLike.builder()
                    .photo(photo)
                    .user(currentUser)
                    .build();
            photoLikeRepository.save(newLike);
            return true;
        }
    }

}
