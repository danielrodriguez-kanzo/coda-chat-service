package com.kanzo.codabackend.coda_chat.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyFilter implements Filter {

    @Value("${app.api-key}")
    private String expectedApiKey;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        if (path.equals("/api/chat/health")) {
            chain.doFilter(request, response);
            return;
        }

        String providedApiKey = httpRequest.getHeader("X-API-Key");

        if (!expectedApiKey.equals(providedApiKey)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Invalid API Key");
            return;
        }

        chain.doFilter(request, response);

    }
}
