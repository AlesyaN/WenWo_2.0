package ru.itis.repositories.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

}
