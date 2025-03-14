package com.cyber08.uniclub.controller;

import com.cyber08.uniclub.payload.response.BaseResponse;
import com.cyber08.uniclub.services.AuthenticationServices;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        boolean success = authenticationServices.authenticate(email, password);

        BaseResponse response = new BaseResponse();
        response.setData(success);
        response.setMessage(success?"Success":"Failed");
        response.setCode(success ? 0 : 1);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestParam String fullname,@RequestParam String email, @RequestParam String password, @RequestParam String passwordConfirm) {
        boolean success = authenticationServices.authenticateSignUp(fullname, email, password, passwordConfirm);

        BaseResponse response = new BaseResponse();
        response.setData(success);
        response.setCode(success ? 0 : 1);
        response.setMessage(success?"Success":"Failed");

        return ResponseEntity.ok(response);
    }
}
