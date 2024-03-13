package com.exam.orm.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    private final Map<String, Long> requestCounts = new ConcurrentHashMap<>();
    private final long timeWindowInMillis = 60000; // 60 seconds
    private final int requestLimit = 2;
    private static long count =0;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        long currentTimeMillis = System.currentTimeMillis();

        // Remove old entries
        requestCounts.entrySet().removeIf(entry -> currentTimeMillis - entry.getValue() > timeWindowInMillis);

        System.out.println(requestCounts);
        // Increment request count for this IP
        requestCounts.put(ipAddress, count++);

        // Check if request count exceeds the limit
        if (requestCounts.getOrDefault(ipAddress, 0L) >= requestLimit) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded. Please try again later.");
            return false; // Block the request
        }

        return true; // Allow the request to proceed
    }
}