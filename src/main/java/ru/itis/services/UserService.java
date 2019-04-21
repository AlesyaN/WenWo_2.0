package ru.itis.services;

import ru.itis.forms.UserRegisterForm;
import ru.itis.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(Integer id);

    boolean signUp(UserRegisterForm form);
}
