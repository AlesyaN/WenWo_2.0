package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.forms.PhotoForm;
import ru.itis.models.Album;
import java.util.Optional;


public interface AlbumService {
    Optional<Album> getAlbum(Integer albumId);

    void addAlbum(Album album);

    void deleteAlbum(Album album);

    void editAlbumName(Integer albumId, String newName);

}
