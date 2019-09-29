package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEditDto {
    private String name;
    private String surname;
    private String login;
    private String email;
    private String gender;
    private String city;
    private String dateOfBirth;

    public static UserEditDto from(User user) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return UserEditDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .email(user.getEmail())
                .gender(user.getGender())
                .city(user.getCity())
                .dateOfBirth(format.format(user.getDateOfBirth()))
                .build();
    }
}
