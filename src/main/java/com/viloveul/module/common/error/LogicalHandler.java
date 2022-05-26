package com.viloveul.module.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class LogicalHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(
        Throwable e,
        HttpServletRequest request
    ) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("code", 403);
        maps.put("message", e.getMessage());
        return new ResponseEntity<>(maps, null, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(
        Throwable e,
        HttpServletRequest request
    ) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("code", 401);
        maps.put("message", e.getMessage());
        return new ResponseEntity<>(maps, null, HttpStatus.UNAUTHORIZED);
    }

}
