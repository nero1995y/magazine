package com.shop.magazine.global.exception;

import com.shop.magazine.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {


    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(
            UserNotFoundException e){
        //log.error("UserNotFoundException", e);
        ErrorResponse response = ErrorResponse.of(ErrorCode.USER_NOT_FOUND);
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
