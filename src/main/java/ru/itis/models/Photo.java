package ru.itis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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


    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    @OrderBy("date")

    private List<PhotoComment> comments;

    private Double coordinateX;
    private Double coordinateY;

    @OneToMany(mappedBy = "photo")
    private List<PhotoLike> likes;

    public String getDateToString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.format(date);
    }
}

