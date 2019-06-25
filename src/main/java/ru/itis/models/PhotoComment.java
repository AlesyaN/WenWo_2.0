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
@Table(name = "comment_photo")
public class PhotoComment extends Comment{

    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;


    @Builder
    public PhotoComment(Integer id, User author, String text, Date date, Photo photo) {
        super(id, author, text, date);
        this.photo = photo;
    }
}
