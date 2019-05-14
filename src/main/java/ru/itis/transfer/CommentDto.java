package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Comment;
import ru.itis.models.User;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String text;
    private Integer questionId;
    private Integer authorId;
    private String authorLogin;
    private Date date;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .authorId(comment.getAuthor().getId())
                .authorLogin(comment.getAuthor().getLogin())
                .questionId(comment.getQuestion().getId())
                .text(comment.getText())
                .date(comment.getDate())
                .build();
    }
}
