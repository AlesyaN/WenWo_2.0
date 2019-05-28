package ru.itis.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileDownloader {

    private static String UPLOADED_FOLDER;

    public Optional<String> upload(MultipartFile image, String folderName) {
        if (!image.isEmpty()) {
            String userFolderName = "/users/" + folderName + "/";
            File directory = new File(UPLOADED_FOLDER + userFolderName);
            if (!directory.exists()) directory.mkdirs();
            String fileName = userFolderName + image.getOriginalFilename();
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            try {
                byte[] bytes = image.getBytes();
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Optional.of(fileName);

        } else {
            return Optional.empty();
        }
    }

    public void createFolder(String folderPath) {
        File directory = new File(UPLOADED_FOLDER + "/users/" + folderPath);
        if (!directory.exists()) directory.mkdirs();
    }

    public void deleteFolder(String folderPath) {
        File directory = new File(UPLOADED_FOLDER + "/users/" + folderPath);
        FileSystemUtils.deleteRecursively(directory);
    }

    @Value("${my.files-url}")
    public void setUploadedFolder(String uploadedFolder) {
        UPLOADED_FOLDER = uploadedFolder;
    }
}
