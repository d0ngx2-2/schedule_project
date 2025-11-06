package com.schedule_project.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExeptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorRespnse> handleCustomException(CustomException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorRespnse errorRespnse = new ErrorRespnse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorRespnse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRespnse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorRespnse errorRespnse = new ErrorRespnse(
                401,
                status.getReasonPhrase(),
                "빈칸입니다 입력해주세요",
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorRespnse, status);
    }
}
