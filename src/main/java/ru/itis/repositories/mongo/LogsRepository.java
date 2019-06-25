package ru.itis.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Log;

@Repository
public interface LogsRepository extends MongoRepository<Log,String> {
}
