package com.cyber08.uniclub.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

public interface AuthenticationServices {
    String authenticate(String username, String password);
    boolean authenticateSignUp(String fullname, String username, String password, String passwordConfirm);
}
