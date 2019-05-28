package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
