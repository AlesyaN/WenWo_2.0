package ru.itis.services;

import ru.itis.forms.PhotoForm;
import ru.itis.models.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoService {
    void addPhoto(PhotoForm photoForm);

    void deletePhoto(Integer photoId);

    Optional<Photo> getPhoto(Integer photoId);

    void editPhotoDescription(Integer photoId, String newDescription);

    List<Photo> getPhotosWithGPS(String login);

    Optional<Photo> getPhotoById(Integer id);
}
