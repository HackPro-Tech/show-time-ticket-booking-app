package com.showtime.authserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.showtime.exception.constant.ApiMessage;
import com.showtime.exception.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;


@Component
public class IAMSecurityAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message(authException.getMessage())
                .error(ApiMessage.UNAUTHORIZED.value())
                .owner(ApiMessage.OWNER.value())
                .path(request.getRequestURI())
                .status(HttpStatus.UNAUTHORIZED.value())
                .build();
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, apiResponse);
        out.flush();
    }
}
