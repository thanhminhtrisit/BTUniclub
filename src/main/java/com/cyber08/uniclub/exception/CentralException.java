package com.cyber08.uniclub.exception;

import com.cyber08.uniclub.payload.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CentralException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(Exception e) {
        BaseResponse response = new BaseResponse();
        response.setCode(500);
        response.setMessage(e.getMessage());

        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleParameter(Exception e) {
        BaseResponse response = new BaseResponse();
        response.setCode(400);
        response.setMessage(e.getMessage());

        return ResponseEntity.status(400).body(response);
    }
}
