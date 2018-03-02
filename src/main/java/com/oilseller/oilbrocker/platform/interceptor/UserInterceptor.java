package com.oilseller.oilbrocker.platform.interceptor;

import com.oilseller.oilbrocker.platform.dto.Context;
import com.oilseller.oilbrocker.platform.thread.ThreadLocalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpMethod.OPTIONS;

@Component
public class UserInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(request.getRequestURI().contains("login") || request.getRequestURI().contains("error")
                || request.getMethod().equals(OPTIONS.toString()))) {
            String token = request.getHeader("token");
            String username = getUsername(token);
            log.info("Request by user : {}", username);

            Context context = new Context(username);
            ThreadLocalContext.setContext(context);
        }
        return true;
    }

    private String getUsername(String token) {
        return "USER";
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
