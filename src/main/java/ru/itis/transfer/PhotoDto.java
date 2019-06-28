package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Photo;

import java.text.SimpleDateFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhotoDto {
    private Double coordinateX;
    private Double coordinateY;
    private String photoPath;

    public static PhotoDto from(Photo photo) {
        return PhotoDto.builder()
                .coordinateX(photo.getCoordinateX())
                .coordinateY(photo.getCoordinateY())
                .photoPath(photo.getPhotoPath())
                .build();
    }
}
