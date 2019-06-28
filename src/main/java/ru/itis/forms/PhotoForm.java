package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.Album;
import ru.itis.validators.File;
import ru.itis.validators.FileNotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoForm {
    private String description;

    @FileNotNull
    @File
    private MultipartFile photo;

    private Album album;

    private Double x;

    private Double y;
}
