package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.PhotoComment;
import ru.itis.models.QuestionComment;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer id;
    private String text;
    private Integer postId;
    private Integer authorId;
    private String authorLogin;
    private Date date;

    public static CommentDto from(QuestionComment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .authorId(comment.getAuthor().getId())
                .authorLogin(comment.getAuthor().getLogin())
                .postId(comment.getQuestion().getId())
                .text(comment.getText())
                .date(comment.getDate())
                .build();
    }

    public static CommentDto from(PhotoComment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .authorId(comment.getAuthor().getId())
                .authorLogin(comment.getAuthor().getLogin())
                .postId(comment.getPhoto().getId())
                .text(comment.getText())
                .date(comment.getDate())
                .build();
    }
}
