package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class UserRegisterForm {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String email;
    private MultipartFile file;

}
