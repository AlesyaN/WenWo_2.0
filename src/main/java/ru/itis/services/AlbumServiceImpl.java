package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Album;
import ru.itis.repositories.AlbumRepository;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public Optional<Album> getAlbum(Integer albumId) {
        return Optional.ofNullable(albumRepository.findOne(albumId));
    }
}
