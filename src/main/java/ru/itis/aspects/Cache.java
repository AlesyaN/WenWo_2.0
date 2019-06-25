package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.itis.forms.PhotoForm;
import ru.itis.models.Album;
import ru.itis.models.Comment;
import ru.itis.models.Photo;
import ru.itis.models.PhotoComment;
import ru.itis.repositories.postgres.AlbumRepository;
import ru.itis.services.PhotoService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@Aspect
public class Cache {

    private Map<Integer, Album> albumsCache;

    public Cache() {
        albumsCache = new HashMap<>();
    }

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    PhotoService photoService;

    @Around("execution(* *..AlbumService.getAlbum(..))")
    public Optional<Album> checkCache(ProceedingJoinPoint jp) {
        if (albumsCache.containsKey(jp.getArgs()[0])) {
            return Optional.ofNullable(albumsCache.get(jp.getArgs()[0]));
        } else {
            Optional<Album> albumOptional = null;
            try {
                albumOptional = (Optional<Album>) jp.proceed(jp.getArgs());
                albumOptional.ifPresent(album -> albumsCache.put(album.getId(), album));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return albumOptional;
        }
    }

    @Before("execution(* *..PhotoService.addPhoto(..))")
    public void updateByPhotoForm(JoinPoint jp) {
        PhotoForm photoForm = (PhotoForm) jp.getArgs()[0];
        Album album = photoForm.getAlbum();
        albumsCache.remove(album.getId());
    }

    @Pointcut("execution(* *..PhotoService.deletePhoto(..))")
    public void deletePhoto() {
    }

    @Pointcut("execution(* *..PhotoService.editPhotoDescription(..))")
    public void editPhotoDescription() {
    }

    @Before("deletePhoto() || editPhotoDescription()")
    public void updateByPhotoId(JoinPoint jp) {
        Integer photoId = (Integer) jp.getArgs()[0];
        Optional<Photo> photoOptional = photoService.getPhoto(photoId);
        if (photoOptional.isPresent()) {
            Photo photo = photoOptional.get();
            albumsCache.remove(photo.getAlbum().getId());
        }
    }


    @Before("execution(* *..AlbumService.editAlbumName(..))")
    public void updateByAlbumId(JoinPoint jp) {
        albumsCache.remove((Integer) jp.getArgs()[0]);
    }

    @Before("execution(* *..AlbumService.deleteAlbum(..))")
    public void updateByAlbum(JoinPoint jp) {
        albumsCache.remove(((Album) jp.getArgs()[0]).getId());
    }

    @Pointcut("execution(* *..PhotoCommentServiceImpl.addComment(..))")
    public void addComment() {
    }

    @Pointcut("execution(* *..PhotoCommentServiceImpl.deleteComment(..))")
    public void deleteComment() {
    }

    @Before("addComment() || deleteComment()")
    public void updateByComment(JoinPoint jp) {
        Comment comment = (PhotoComment) jp.getArgs()[0];
        albumsCache.remove(((PhotoComment) comment).getPhoto().getAlbum().getId());
    }

}
