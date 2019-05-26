package ru.itis.services;

import ru.itis.models.Message;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<Message> getMessageById(Integer id);
    List<Message> getMessagesByUsers(User user1, User user2);
    void addMessage(Message message);
    void deleteMessage(Message message, User sender);
    List<Message> getChats(User currentUser);

}
