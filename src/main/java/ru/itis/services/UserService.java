package ru.itis.services;

import org.springframework.security.core.Authentication;
import ru.itis.forms.UserEditForm;
import ru.itis.forms.UserRegisterForm;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(Integer id);

    boolean signUp(UserRegisterForm form);

    boolean toggleSubscription(User user, User currentUser);

    boolean editProfile(UserEditForm form, User user);

    boolean loginIsUnique(String login);

    boolean emailIsUnique(String email);

    Optional<User> getCurrentUser(Authentication authentication);

    List<User> searchUsers(String text);
}
