package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reciever_id")
    private User receiver;

    @OneToMany(mappedBy = "question")
    private List<Like> likes;

    @Column(name = "message")
    private String text;
    private Date date;
    private String answer;

}
