package com.cyber08.uniclub.controller;

import com.cyber08.uniclub.payload.request.SignUpRequest;
import com.cyber08.uniclub.payload.response.BaseResponse;
import com.cyber08.uniclub.services.AuthenticationServices;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

/*
    {
        "code": 200,
        "message": "...",
        "data":
    }

    payload: quản lí request và response

    @CrossOrigin: mặc định cho tất cả ip và domain đi vào
    @CrossOrigin("/...."): các được dẫn được chỉ định
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationServices authenticationServices;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password) {
        String token = authenticationServices.authenticate(email, password);

        BaseResponse response = new BaseResponse();
        response.setData(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        BaseResponse response = new BaseResponse();
        response.setData(signUpRequest);

        return ResponseEntity.ok(response);
    }

}
