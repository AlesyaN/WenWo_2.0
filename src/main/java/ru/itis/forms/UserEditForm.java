package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    public static User from(UserEditForm form) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return User.builder()
                    .name(form.getName())
                    .surname(form.getSurname())
                    .login(form.getLogin())
                    .password(form.getNewPassword())
                    .email(form.getEmail())
                    .gender(form.getGender())
                    .city(form.getCity())
                    .dateOfBirth(format.parse(form.getDateOfBirth()))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
