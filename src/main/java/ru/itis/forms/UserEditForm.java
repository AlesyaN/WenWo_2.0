package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditForm {
    private String name;
    private String surname;
    private String login;
    private String newPassword;
    private String oldPassword;
    private String email;
    private String gender;
    private String city;
    private String dateOfBirth;
    private MultipartFile file;


}
