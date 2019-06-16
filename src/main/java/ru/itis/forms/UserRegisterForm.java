package ru.itis.forms;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.validators.File;
import ru.itis.validators.Login;
import ru.itis.validators.Password;
import ru.itis.validators.UniqueEmail;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class UserRegisterForm {
    @Login
    private String login;

    @Password
    @NotEmpty
    private String password;

    @NotEmpty
    @Size(min=4, max=30)
    private String name;

    @NotEmpty
    @Size(min=4, max=30)
    private String surname;

    @NotEmpty
    @UniqueEmail
    @Email
    private String email;

    @File
    private MultipartFile file;

}
