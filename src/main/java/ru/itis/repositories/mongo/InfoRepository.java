package ru.itis.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Info;

@Repository
public interface InfoRepository extends MongoRepository<Info, Integer> {
    Info findTopByOrderByUpdatedDesc();
}
