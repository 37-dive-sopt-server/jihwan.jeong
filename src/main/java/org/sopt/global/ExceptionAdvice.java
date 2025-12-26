package org.sopt.global;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e) {
        return new ApiResponse<>(
                400,
                e.getMessage(),
                null
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiResponse<?> handleException(DataIntegrityViolationException e) {
        return new ApiResponse<>(
                400,
                "아티클 제목은 중복될 수 없습니다.",
                null
        );
    }
}
