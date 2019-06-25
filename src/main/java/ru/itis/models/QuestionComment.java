package ru.itis.models;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "comment_question")
public class QuestionComment extends Comment{
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Builder
    public QuestionComment(Integer id, User author, String text, Date date, Question question) {
        super(id, author, text, date);
        this.question = question;
    }
}
