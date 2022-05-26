package com.viloveul.module.common.error;

import com.viloveul.context.exception.FailureException;
import com.viloveul.context.exception.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class FailureHandler {

    @ExceptionHandler(FailureException.class)
    public ResponseEntity<Map<String, Object>> handleGeneralFailureException(
        ErrorMessage e,
        HttpServletRequest request
    ) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("code", e.getCode());
        maps.put("message", e.getMessage());
        maps.put("cause", e.getCause());
        return new ResponseEntity<>(maps, null, HttpStatus.valueOf(e.getStatus()));
    }

}
