package ru.myshop.marketplace.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.myshop.marketplace.models.User;
import ru.myshop.marketplace.models.enums.Role;
import ru.myshop.marketplace.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean saveUser(User user) {

        User checkInUser = userRepository.findByEmail(user.getEmail());

        if (checkInUser != null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.set_active(true);
        user.setRoles(new HashSet<>(Collections.singletonList(Role.ROLE_ADMIN)));
        userRepository.save(user);
        return true;
    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
