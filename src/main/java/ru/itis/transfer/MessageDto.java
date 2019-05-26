package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Message;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private Integer id;
    private String senderLogin;
    private String receiverLogin;
    private String text;
    private Date date;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .senderLogin(message.getSender().getLogin())
                .receiverLogin(message.getReceiver().getLogin())
                .text(message.getText())
                .date(message.getDate())
                .build();

    }
}
