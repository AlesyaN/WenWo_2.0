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

    public static AlbumDto from(Album album) {
        return AlbumDto.builder()
                .id(album.getId())
                .name(album.getName())
                .cover(album.getPhotos().get(0))
                .build();
    }
}
