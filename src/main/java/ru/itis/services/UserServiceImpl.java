package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.forms.UserEditForm;
import ru.itis.forms.UserRegisterForm;
import ru.itis.models.User;
import ru.itis.repositories.postgres.UserRepository;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.utils.FileDownloader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private FileDownloader fileDownloader;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean emailIsUnique(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> getCurrentUser(Authentication authentication) {
        if (authentication != null) {
            Integer currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
            return getUserById(currentUserId);
        }
        return Optional.empty();
    }

    @Override
    public List<User> searchUsers(String text) {
        return userRepository.search(text);
    }

    public boolean loginIsUnique(String login) {
        return !userRepository.findByLogin(login).isPresent();
    }


    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean signUp(UserRegisterForm form) {
        String hashPassword = passwordEncoder.encode(form.getPassword());
        User user = User.builder()
                .login(form.getLogin())
                .password(hashPassword)
                .email(form.getEmail())
                .name(form.getName())
                .surname(form.getSurname())
                .build();
        if (!form.getFile().isEmpty()) {
            user.setPhoto_path(fileDownloader.upload(form.getFile(), form.getLogin()).orElseThrow(IllegalArgumentException::new));
        }
        userRepository.save(user);
        return true;
    }


    @Override
    public boolean toggleSubscription(User subscriptor, User subscriber) {
        if (subscriptionIsUnique(subscriptor, subscriber)) {
            subscriptor.getFollowers().add(subscriber);
            userRepository.save(subscriptor);
            return true;
        } else {
            subscriptor.getFollowers().remove(findById(subscriptor.getFollowers(), subscriber.getId()));
            userRepository.save(subscriptor);
            return false;
        }
    }

    @Override
    public boolean editProfile(UserEditForm form, User currentUser) {
        if (form.getOldPassword().equals("") && form.getNewPassword().equals("")
                || passwordEncoder.matches(form.getOldPassword(), currentUser.getPassword())) {
            if (!form.getName().equals(""))
                currentUser.setName(form.getName());
            else
                currentUser.setName(null);

            if (!form.getSurname().equals(""))
                currentUser.setSurname(form.getSurname());
            else
                currentUser.setSurname(null);

            if (!form.getLogin().equals(""))
                currentUser.setLogin(form.getLogin());
            else
                currentUser.setLogin(null);

            if (!form.getEmail().equals(""))
                currentUser.setEmail(form.getEmail());
            else
                currentUser.setEmail(null);

            if (!form.getOldPassword().equals("") && !form.getNewPassword().equals(""))
                currentUser.setPassword(passwordEncoder.encode(form.getNewPassword()));

            if (!form.getCity().equals(""))
                currentUser.setCity(form.getCity());
            else
                currentUser.setCity(null);

            if (!form.getGender().equals(""))
                currentUser.setGender(form.getGender());
            else
                currentUser.setGender(null);

            if (!form.getDateOfBirth().equals("")) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    currentUser.setDateOfBirth(format.parse(form.getDateOfBirth()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                currentUser.setDateOfBirth(null);
            }

            if (!form.getFile().isEmpty()) {
                String path = fileDownloader.upload(form.getFile(), currentUser.getLogin()).orElseThrow(IllegalArgumentException::new);
                currentUser.setPhoto_path(path);
            }

            userRepository.save(currentUser);
            return true;
        }
        return false;
    }

    private User findById(List<User> users, Integer id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    private boolean subscriptionIsUnique(User subscriptor, User subscriber) {
        for (User follower : subscriptor.getFollowers()) {
            if (follower.getId().equals(subscriber.getId())) {
                return false;
            }
        }
        return true;
    }
}
