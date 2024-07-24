package com.b1nd.alimoserver.global.error;

import com.b1nd.alimoserver.global.error.exception.BusinessException;
import com.b1nd.alimoserver.global.error.exception.StatusEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )throws IOException {

        try {
            filterChain.doFilter(request,response);
        } catch (BusinessException e){
            StatusEnum errorCode = e.getErrorCode();
            writerErrorResponse(response, ErrorResponse.of(errorCode, errorCode.getMessage()));
        } catch (Exception e){
            writerErrorResponse(response, ErrorResponse.of(response.getStatus(),e.getMessage()));
        }
    }

    private void writerErrorResponse(HttpServletResponse response, ErrorResponse errorResponse) throws IOException{
        response.setStatus(errorResponse.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }

}