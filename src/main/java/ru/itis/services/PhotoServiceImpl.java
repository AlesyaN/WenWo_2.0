package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.forms.PhotoForm;
import ru.itis.models.Photo;
import ru.itis.repositories.PhotoRepository;
import ru.itis.utils.FileDownloader;

import java.util.Date;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    FileDownloader fileDownloader;

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public void addPhoto(PhotoForm photoForm) {
        String photoPath = fileDownloader.upload(photoForm.getPhoto(),
                photoForm.getAlbum().getOwner().getLogin()
                        + "/albums/"
                        + photoForm.getAlbum().getId()).orElseThrow(IllegalArgumentException::new);
        Photo photo = Photo.builder()
                .photoPath(photoPath)
                .description(photoForm.getDescription())
                .album(photoForm.getAlbum())
                .date(new Date())
                .build();
        photoRepository.save(photo);
    }

    @Override
    public void deletePhoto(Integer photoId) {
        Photo photo = getPhoto(photoId).orElseThrow(IllegalArgumentException::new);
        fileDownloader.delete(photo.getPhotoPath());
        photoRepository.delete(photo);
    }

    @Override
    public Optional<Photo> getPhoto(Integer photoId) {
        return Optional.ofNullable(photoRepository.findOne(photoId));
    }
}
