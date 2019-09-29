package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Album;
import ru.itis.models.Photo;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumDto {
    private Integer id;
    private String name;
    private Photo cover;
    private Integer photosNumber;

    private static final String DEFAULT_PHOTO_PATH = "/users/default.png";

    public static AlbumDto from(Album album) {
        Photo photo;
        Integer photosNumber;
        if (album.getPhotos() == null || album.getPhotos().isEmpty()) {
            photo = Photo.builder()
                    .photoPath(DEFAULT_PHOTO_PATH).build();
            photosNumber = 0;
        } else {
            photo = album.getPhotos().get(0);
            photosNumber = album.getPhotos().size();
        }
        return AlbumDto.builder()
                .id(album.getId())
                .name(album.getName())
                .photosNumber(photosNumber)
                .cover(photo)
                .build();
    }
}
