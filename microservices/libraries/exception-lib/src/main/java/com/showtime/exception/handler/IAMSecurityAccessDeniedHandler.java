/*
package com.showtime.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.showtime.exception.constant.ApiMessage;
import com.showtime.exception.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

*/
/**
 * @author Vengatesan Nagarajan
 *//*

@Component
public class IAMSecurityAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException, IOException {
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getMessage())
                .error(ApiMessage.FORBIDDEN.value())
                .owner(ApiMessage.OWNER.value())
                .path(request.getRequestURI())
                .status(HttpStatus.FORBIDDEN.value())
                .build();
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, apiResponse);
        out.flush();
    }
}
*/
