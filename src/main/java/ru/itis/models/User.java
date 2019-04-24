package ru.itis.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hib_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String password;
    private String photo_path;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String gender;
    @Column(name = "dateofbirth")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Question> questions;

    @ManyToMany
    @JoinTable(
            name="subscriptions",
            joinColumns = @JoinColumn(name="subscriptor_id"),
            inverseJoinColumns = @JoinColumn(name="subscriber_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> followings;



    public String getFullName() {
        return name + " " + surname;
    }

    public List<Question> getAnsweredQuestions() {
        List<Question> questions = new ArrayList<>();
        for (Question question: this.questions) {
            if (question.getAnswer() != null)
            questions.add(question);
        }
        return questions;
    }

    public List<Question> getUnansweredQuestions() {
        List<Question> questions = new ArrayList<>();
        for (Question question: this.questions) {
            if (question.getAnswer() == null)
                questions.add(question);
        }
        return questions;
    }

}
