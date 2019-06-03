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
@Table(name = "albums_photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "photo_path")
    private String photoPath;

    private String description;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    private Date date;

    @OneToMany(mappedBy = "photo")
    private List<PhotoComment> comments;
}

