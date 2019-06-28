package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.repositories.postgres.MessageRepository;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
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

    @Override
    public List<Message> getChats(User user) {
        List<Message> messages =  messageRepository.findAllByUser(user.getId());
        Map<User, Message> chatMap = new HashMap<>();
        for (Message m: messages) {
            User partner;
            if (m.getSender().getId().equals(user.getId())) {
                partner = m.getReceiver();
            } else {
                partner = m.getSender();
            }
            if (!chatMap.containsKey(partner) || chatMap.get(partner).getDate().before(m.getDate())) {
                chatMap.put(partner, m);
            }
        }
        List<Message> chats = new ArrayList<>(chatMap.values());
        chats.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                if (o1.getDate().before(o2.getDate())) {
                    return 1;
                } else if (o1.getDate().after(o2.getDate())){
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return chats;
    }

    @Override
    public Optional<Message> getLastMessageByUsers(User user1, User user2) {
        return Optional.ofNullable(messageRepository.findLastMessageByUsers(user1.getId(), user2.getId()));
    }

}
