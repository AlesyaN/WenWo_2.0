package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.forms.PhotoForm;
import ru.itis.models.Album;
import ru.itis.repositories.AlbumRepository;
import ru.itis.utils.FileDownloader;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    FileDownloader fileDownloader;

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public Optional<Album> getAlbum(Integer albumId) {
        return Optional.ofNullable(albumRepository.findOne(albumId));
    }

    @Override
    public void addAlbum(Album album) {
        albumRepository.save(album);
        fileDownloader.createFolder(album.getOwner().getLogin() + "/albums/" + album.getId());
    }

}
