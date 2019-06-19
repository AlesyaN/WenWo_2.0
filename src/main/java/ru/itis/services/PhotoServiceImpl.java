package ru.itis.services;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.forms.PhotoForm;
import ru.itis.models.Photo;
import ru.itis.repositories.postgres.PhotoRepository;
import ru.itis.utils.FileDownloader;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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

        Optional<GeoLocation> location = getGPS(fileDownloader.getUploadedFolder() + photoPath);
        if (location.isPresent()) {
            photo.setCoordinateX(location.get().getLatitude());
            photo.setCoordinateY(location.get().getLongitude());
        } else {
            photo.setCoordinateX(photoForm.getX());
            photo.setCoordinateY(photoForm.getY());
        }

        photoRepository.save(photo);
    }

    private Optional<GeoLocation> getGPS(String path) {
        File file = new File(path);
        GpsDirectory gpsDirectory = null;
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            gpsDirectory = metadata.getFirstDirectoryOfType(GpsDirectory.class);
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(gpsDirectory != null ? gpsDirectory.getGeoLocation() : null);
    }

    @Override
    public void deletePhoto(Integer photoId) {
        Photo photo = getPhoto(photoId).orElseThrow(IllegalArgumentException::new);
        fileDownloader.delete(photo.getPhotoPath());
        photoRepository.delete(photo);
    }

    @Override
    public Optional<Photo> getPhoto(Integer photoId) {
        return photoRepository.findById(photoId);
    }

    @Override
    public void editPhotoDescription(Integer photoId, String newDescription) {
        Photo photo = getPhoto(photoId).orElseThrow(IllegalArgumentException::new);
        photo.setDescription(newDescription);
        photoRepository.save(photo);
    }

    @Override
    public List<Photo> getPhotosWithGPS(String login) {
        return photoRepository.getPhotosWithGPS(login);
    }


}
