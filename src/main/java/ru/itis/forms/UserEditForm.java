package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.User;
import ru.itis.validators.Login;
import ru.itis.validators.Password;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditForm {
    private String name;
    private String surname;

    @Login
    private String login;

    @Password
    private String newPassword;
    private String oldPassword;
    private String email;
    private String gender;
    private String city;
    private String dateOfBirth;
    private MultipartFile file;


}
