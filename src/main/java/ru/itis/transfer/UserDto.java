package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.models.Album;
import ru.itis.models.Question;
import ru.itis.models.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private String photoPath;
    private Date dateOfBirth;
    private List<Question> answeredQuestions;
    private List<Question> unansweredQuestions;
    private List<User> followers;
    private List<User> followings;
    private List<AlbumDto> albums;

    private static String UPLOADED_FOLDER;

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
                .photoPath(user.getPhoto_path())
                .unansweredQuestions(user.getUnansweredQuestions())
                .answeredQuestions(user.getAnsweredQuestions())
                .albums(user.getAlbums().stream().map(AlbumDto::from).collect(Collectors.toList()))
                .build();
    }

    @Value("${my.files-url}")
    public void setUploadedFolder(String uploadedFolder) {
        UPLOADED_FOLDER = uploadedFolder;
    }

}
