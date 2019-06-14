package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.models.Photo;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    @Query("from Photo p where p.coordinateX is not null and p.coordinateY is not null and p.album.owner.login=:login")
    List<Photo> getPhotosWithGPS(@Param("login") String login);
}
