package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.User;
import ru.itis.validators.File;
import ru.itis.validators.Login;
import ru.itis.validators.Password;
import ru.itis.validators.UniqueEmail;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditForm {
    @NotBlank
    @Size(min=4, max=30)
    private String name;

    @NotBlank
    @Size(min=4, max=30)
    private String surname;

    @Login
    private String login;

    @Password
    private String newPassword;
    private String oldPassword;

    @NotBlank
    @UniqueEmail
    @Email
    private String email;

    private String gender;
    private String city;

    @Past
    private String dateOfBirth;

    @File
    private MultipartFile file;


}
