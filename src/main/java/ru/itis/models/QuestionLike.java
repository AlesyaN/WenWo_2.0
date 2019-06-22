package ru.itis.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "question_like")
public class QuestionLike extends Like {

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Builder
    public QuestionLike(Integer id, User user, Question question) {
        super(id, user);
        this.question = question;
    }

}
