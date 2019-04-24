package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.forms.UserRegisterForm;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.util.List;
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



    @Override
    public boolean toggleSubscription(User subscriptor, User subscriber) {
        if (subscriptionIsUnique(subscriptor, subscriber)) {
            subscriptor.getFollowers().add(subscriber);
            subscriber.getFollowings().add(subscriptor);
            userRepository.save(subscriptor);
            return true;
        } else {
            subscriptor.getFollowers().remove(findById(subscriptor.getFollowers(), subscriber.getId()));
            subscriber.getFollowings().remove(findById(subscriber.getFollowings(), subscriptor.getId()));
            userRepository.save(subscriptor);
            return false;
        }
    }

    private User findById(List<User> users,Integer id) {
        for (User user: users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    private boolean subscriptionIsUnique(User subscriptor, User subscriber) {
        for (User follower: subscriptor.getFollowers()) {
            if (follower.getId().equals(subscriber.getId())) {
                return false;
            }
        }
        return true;
    }
}
