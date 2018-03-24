package com.oilseller.oilbrocker.platform.interceptor;

import com.oilseller.oilbrocker.platform.dto.Context;
import com.oilseller.oilbrocker.platform.thread.ThreadLocalContext;
import com.oilseller.oilbrocker.user.service.TokenService;
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

    private TokenService tokenService;

    public UserInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(isaAuthenticateRequired(request)) {
            String authorization = request.getHeader("Authorization");
            String username = getUsername(authorization);
            log.info("Request by user : {}", username);

            Context context = new Context(username);
            ThreadLocalContext.setContext(context);
        }
        return true;
    }

    private boolean isaAuthenticateRequired(HttpServletRequest request) {
        return !(request.getRequestURI().contains("login") || request.getRequestURI().contains("error")
                || request.getMethod().equals(OPTIONS.toString()) || request.getRequestURI().contains("order/place")
                || request.getRequestURI().contains("product/list") || request.getRequestURI().contains("product/load")
                || request.getRequestURI().contains("order/load")
        );
    }

    private String getUsername(String userToken) {
        return tokenService.getUsername(userToken);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadLocalContext.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
