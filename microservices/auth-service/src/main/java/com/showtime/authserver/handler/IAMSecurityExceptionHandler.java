package com.showtime.authserver.handler;

import com.showtime.exception.IAMAuthenticationException;
import com.showtime.exception.UserAlreadyExistsException;
import com.showtime.exception.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Vengatesan Nagarajan
 */
@ControllerAdvice
public class IAMSecurityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String OWNER = "com.hackpro";
    private static final String SERVER_ERROR = "Internal Server ";
    private static final String CONFLICT_ERROR = "Conflict";

    @ExceptionHandler({IAMAuthenticationException.class, UsernameNotFoundException.class,
            UserAlreadyExistsException.class})
    public ResponseEntity<ApiResponse> handleAuthenticationException(Exception ex, HttpServletRequest httpRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getMessage())
                .error(CONFLICT_ERROR)
                .owner(OWNER)
                .path(httpRequest.getRequestURI())
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAllException(Exception ex, HttpServletRequest httpRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getMessage())
                .error(SERVER_ERROR)
                .owner(OWNER)
                .path(httpRequest.getRequestURI())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}