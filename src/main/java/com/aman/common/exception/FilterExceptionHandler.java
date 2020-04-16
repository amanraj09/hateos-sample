package com.aman.common.exception;

import com.aman.security.exception.AuthSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterExceptionHandler extends OncePerRequestFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);

        } catch (AuthSecurityException e) {
            LOGGER.error("{}: {}", e.getClass().getSimpleName(), e.getMessage());
            new ApiErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    e.getMessage()
            );
        } catch (RuntimeException e) {
            LOGGER.error("{}: {}", e.getClass().getSimpleName(), e.getMessage());
            new ApiErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    e.getMessage()
            );
        }
    }
}
