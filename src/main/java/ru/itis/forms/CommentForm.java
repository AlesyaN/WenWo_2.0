package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.models.Comment;
import ru.itis.models.Question;
import ru.itis.services.QuestionService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {
    @NotNull
    private Integer questionId;

    @NotEmpty
    private String text;
}
