package ru.itis.services;

import org.springframework.stereotype.Service;
import ru.itis.models.Album;
import java.util.Optional;


public interface AlbumService {
    Optional<Album> getAlbum(Integer albumId);
}
