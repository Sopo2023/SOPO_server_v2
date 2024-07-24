package com.b1nd.alimoserver.global.error;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //비즈니스 로직에서의 에러
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error("CustomException message : {}", e.getMessage());

        StatusEnum errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());
        e.printStackTrace();

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatusCode()));
    }

    // validation 에러
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {

        StatusEnum errorCode = StatusEnum.BAD_REQUEST;
        ErrorResponse response = ErrorResponse.of(errorCode, errorCode.getMessage());
        e.printStackTrace();

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatusCode()));
    }


    //그 외 에러들
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("에러", e);

        StatusEnum errorCode = StatusEnum.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.of(errorCode, e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}