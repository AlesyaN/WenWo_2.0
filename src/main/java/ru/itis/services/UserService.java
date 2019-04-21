package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getCurrentUser() {
//        return Session.getSession().getCurrentUser();
        //TODO return current user
        return null;
    }

    public Optional<User> register(String login, String email, String password) {
        if (loginIsValid(login) && emailIsValid(email)) {
            User user = User.builder()
                    .login(login)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .build();
            user = userRepository.save(user);
            authorize(user);
            return Optional.of(user);
        } else return Optional.empty();
    }

    private boolean emailIsValid(String email) {
        return email != null && !userRepository.findByEmail(email).isPresent();
    }

    private boolean loginIsValid(String login) {
        return login != null && !userRepository.findByLogin(login).isPresent();
    }

    public Optional<User> authenticate(String login, String password) {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            authorize(userOptional.get());
            return userOptional;
        } else return Optional.empty();
    }

    public void authorize (User user){
//        Session.getSession().setCurrentUser(user);
        //todo authorization
    }


    public void logOut(){
//        Session.getSession().setCurrentUser(null);
    //todo logout
    }

    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }
}
