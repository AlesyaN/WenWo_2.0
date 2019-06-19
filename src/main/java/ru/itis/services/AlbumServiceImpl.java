package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Album;
import ru.itis.repositories.postgres.AlbumRepository;
import ru.itis.utils.FileDownloader;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    FileDownloader fileDownloader;

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public Optional<Album> getAlbum(Integer albumId) {
        return albumRepository.findById(albumId);
    }

    @Override
    public void addAlbum(Album album) {
        albumRepository.save(album);
        fileDownloader.createFolder(album.getOwner().getLogin() + "/albums/" + album.getId());
    }

    @Override
    public void deleteAlbum(Album album) {
        fileDownloader.delete("/users/" + album.getOwner().getLogin() + "/albums/" + album.getId());
        albumRepository.delete(album);
    }

    @Override
    public void editAlbumName(Integer albumId, String newName) {
        Album album = getAlbum(albumId).orElseThrow(IllegalArgumentException::new);
        album.setName(newName);
        albumRepository.save(album);
    }


}
