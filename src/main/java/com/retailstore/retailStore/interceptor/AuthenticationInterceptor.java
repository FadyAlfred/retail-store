package com.retailstore.retailStore.interceptor;

import com.retailstore.retailStore.Config;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String userId = getUserId(request);
        if (userId == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        request.setAttribute(Config.USER_ID, userId);
        return true;
    }

    private String getUserId(HttpServletRequest request) {
        return request.getHeader(Config.TOKEN_HEADER_NAME);
    }
}
