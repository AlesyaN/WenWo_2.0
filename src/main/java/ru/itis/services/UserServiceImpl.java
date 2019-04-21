package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.forms.UserRegisterForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean emailIsValid(String email) {
        return email != null && !userRepository.findByEmail(email).isPresent();
    }

    private boolean loginIsValid(String login) {
        return login != null && !userRepository.findByLogin(login).isPresent();
    }


    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public boolean signUp(UserRegisterForm form) {
        if (!emailIsValid(form.getEmail()) && !loginIsValid(form.getLogin())) return false;
        String hashPassword = passwordEncoder.encode(form.getPassword());
        User user = User.builder()
                .login(form.getLogin())
                .password(hashPassword)
                .email(form.getEmail())
                .name(form.getName())
                .surname(form.getSurname())
                .build();
        userRepository.save(user);
        return true;
    }
}
