package com.cyber08.uniclub.services;

import com.cyber08.uniclub.entity.Role;
import com.cyber08.uniclub.entity.User;
import com.cyber08.uniclub.respository.RoleRepository;
import com.cyber08.uniclub.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServicesImp implements AuthenticationServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleServices roleServices;

    @Override
    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public boolean authenticateSignUp(String fullname, String username, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            return false;
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }

        User user = new User();
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Role defaultRole = roleServices.getDefaultRole();
        user.setRole(defaultRole);

        userRepository.save(user);
        return true;
    }
}
