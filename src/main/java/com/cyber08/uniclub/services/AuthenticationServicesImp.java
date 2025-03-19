package com.cyber08.uniclub.services;

import com.cyber08.uniclub.entity.Role;
import com.cyber08.uniclub.entity.User;
import com.cyber08.uniclub.respository.RoleRepository;
import com.cyber08.uniclub.respository.UserRepository;
import com.cyber08.uniclub.utils.JwtHelper;
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

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String authenticate(String username, String password) {
        String data = jwtHelper.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJIZWxsbyJ9.8YTr2DBu5qb85hVE8BNU63uTg5uimLlOQHk1YyPbkp0");
        System.out.println(data);

        String token = "";
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                token = jwtHelper.generateToken("Hello");
            }
        }
        return token;
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
