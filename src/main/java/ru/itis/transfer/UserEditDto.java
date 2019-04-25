package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.User;

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
    private Date dateOfBirth;

    public static UserEditDto from(User user) {
        return UserEditDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .email(user.getEmail())
                .gender(user.getGender())
                .city(user.getCity())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
