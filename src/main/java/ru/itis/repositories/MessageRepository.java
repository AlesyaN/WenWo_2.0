package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("from Message m where m.sender.id = :id1 and m.receiver.id = :id2 or m.sender.id = :id2 and m.receiver.id = :id1 order by m.date")
    List<Message> findAllByUsers(@Param("id1") Integer id1, @Param("id2") Integer id2);

    @Query("from Message m where m.sender.id = :userId or m.receiver.id = :userId")
    List<Message> findAllByUser(@Param("userId") Integer userId);

    @Query(value = "select * from message where sender_id = :id1 and reciever_id = :id2 or sender_id = :id2 and reciever_id = :id1 order by date desc limit 1",
    nativeQuery = true)
    Message findLastMessageByUsers(@Param("id1") Integer id1, @Param("id2") Integer id2);
}
