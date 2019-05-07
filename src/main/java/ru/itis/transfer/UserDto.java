package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.models.Question;
import ru.itis.models.User;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String login;
    private String email;
    private String fullName;
    private String gender;
    private String city;
    private Date dateOfBirth;
    private List<Question> answeredQuestions;
    private List<Question> unansweredQuestions;
    private List<User> followers;
    private List<User> followings;


    public static UserDto from(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .city(user.getCity())
                .dateOfBirth(user.getDateOfBirth())
                .followers(user.getFollowers())
                .followings(user.getFollowings())
                .unansweredQuestions(user.getUnansweredQuestions())
                .answeredQuestions(user.getAnsweredQuestions())
                .build();
    }

}
