package ru.itis.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "receiver")
    @OrderBy("date DESC")
    private List<Question> questions;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="subscriptions",
            joinColumns = @JoinColumn(name="subscriptor_id"),
            inverseJoinColumns = @JoinColumn(name="subscriber_id")
    )
    private List<User> followers;

    @ManyToMany(mappedBy = "followers")
    private List<User> followings;

    @OneToMany(mappedBy = "owner")
    List<Album> albums;


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
