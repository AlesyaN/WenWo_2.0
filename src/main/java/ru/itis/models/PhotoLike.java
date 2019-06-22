package ru.itis.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "photo_like")
public class PhotoLike extends Like{

    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @Builder
    public PhotoLike(Integer id, User user, Photo photo) {
        super(id, user);
        this.photo = photo;
    }

}
