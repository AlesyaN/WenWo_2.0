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
    private boolean followedByCurrentUser;

    public static UserDto from(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .city(user.getCity())
                .dateOfBirth(user.getDateOfBirth())
                .answeredQuestions(user.getAnsweredQuestions())
                .unansweredQuestions(user.getUnansweredQuestions())
                .followers(user.getFollowers())
                .followings(user.getFollowings())
                .build();
    }

    public static UserDto from(User user, User currentUser) {
        UserDto userDto = UserDto.from(user);
        userDto.followedByCurrentUser = false;
        List<User> followers = user.getFollowers();
        for (int i = 0; i < followers.size() && !userDto.followedByCurrentUser; i++) {
            if (followers.get(i).getId().equals(currentUser.getId()))
                userDto.followedByCurrentUser = true;
        }
        return userDto;
    }
}
