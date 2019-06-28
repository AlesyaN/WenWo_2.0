package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.forms.DeleteMessageForm;
import ru.itis.forms.MessageForm;
import ru.itis.models.Message;
import ru.itis.models.User;
import ru.itis.services.MessageService;
import ru.itis.services.UserService;
import ru.itis.transfer.MessageDto;
import ru.itis.transfer.SimpleUserDto;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/chat")
    public String getChatsPage(ModelMap modelMap, Authentication authentication) {
        User currentUser = userService.getCurrentUser(authentication).orElseThrow(IllegalAccessError::new);
        List<Message> chats = messageService.getChats(currentUser);
        modelMap.addAttribute("chats", chats);

        modelMap.addAttribute("currentUser", SimpleUserDto.from(currentUser));

        return "chats";
    }

    @GetMapping(value = "/chat", params = {"login"})
    public String getChatPage(@RequestParam("login") String login, ModelMap modelMap, Authentication authentication) {
        User user1 = userService.getCurrentUser(authentication).orElseThrow(IllegalArgumentException::new);
        User user2 = userService.getUserByLogin(login).orElseThrow(IllegalArgumentException::new);
        List<Message> messages = messageService.getMessagesByUsers(user1, user2);
        modelMap.addAttribute("messages", messages);
        modelMap.addAttribute("partner", login);
        return "chat";
    }

    @MessageMapping("/message")
    public void chat(@Valid @Payload MessageForm messageForm, Authentication authentication) {
        Message message = Message.builder()
                .sender(userService.getCurrentUser(authentication).orElseThrow(IllegalAccessError::new))
                .receiver(userService.getUserByLogin(messageForm.getReceiverLogin()).orElseThrow(IllegalArgumentException::new))
                .text(messageForm.getText())
                .date(new Date())
                .build();
        messageService.addMessage(message);
        MessageDto messageDto = MessageDto.from(message);
        template.convertAndSendToUser(message.getSender().getLogin(), "/queue/chat", messageDto);
        template.convertAndSendToUser(message.getReceiver().getLogin(), "/queue/chat", messageDto);
        template.convertAndSendToUser(message.getReceiver().getLogin(), "/queue/update", messageDto);
    }

    @MessageMapping("/deleteMessage")
    public void deleteMessage(@Payload DeleteMessageForm deleteMessageForm, Authentication authentication) throws IllegalAccessException {
        Message message = messageService.getMessageById(deleteMessageForm.getMessageId()).orElseThrow(IllegalArgumentException::new);
        User currentUser = userService.getCurrentUser(authentication).orElseThrow(IllegalAccessError::new);
        messageService.deleteMessage(message, currentUser);
        template.convertAndSendToUser(deleteMessageForm.getPartnerLogin(), "/queue/deleteMessage", deleteMessageForm.getMessageId());
        template.convertAndSendToUser(currentUser.getLogin(), "/queue/deleteMessage", deleteMessageForm.getMessageId());
        Optional<Message> lastMessageOptional = messageService.getLastMessageByUsers(currentUser, userService.getUserByLogin(deleteMessageForm.getPartnerLogin()).orElseThrow(IllegalArgumentException::new));
        if (lastMessageOptional.isPresent()) {
            template.convertAndSendToUser(deleteMessageForm.getPartnerLogin(), "/queue/update",
                    MessageDto.from(lastMessageOptional.get()));
        } else {
            template.convertAndSendToUser(deleteMessageForm.getPartnerLogin(), "/queue/update", MessageDto.builder().senderLogin(currentUser.getLogin()).build());
        }
    }
}
