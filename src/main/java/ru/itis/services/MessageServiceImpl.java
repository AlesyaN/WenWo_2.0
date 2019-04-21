package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.repositories.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    public Optional<Message> getMessageById(Integer id) {
        return Optional.ofNullable(messageRepository.findOne(id));
    }

    public List<Message> getMessagesByUsers(User user1, User user2) {
        return messageRepository.findAllByUsers(user1.getId(), user2.getId());
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public void deleteMessage(Message message, User sender) {
        if (message.getSender().getId().equals(sender.getId())) {
            messageRepository.delete(message);
        }
    }

}
