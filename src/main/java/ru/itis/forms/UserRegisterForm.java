package ru.itis.forms;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.validators.File;
import ru.itis.validators.Login;
import ru.itis.validators.Password;

import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class UserRegisterForm {
    @Login
    private String login;

    @Password
    private String password;

    @NotBlank
    @Size(min=4, max=30)
    private String name;

    @NotBlank
    @Size(min=4, max=30)
    private String surname;

    @NotBlank
    @Email
    private String email;

    @File
    private MultipartFile file;

}
