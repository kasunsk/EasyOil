package com.oilseller.oilbrocker.platform.interceptor;


import com.oilseller.oilbrocker.user.service.AuthService;
import com.oilseller.oilbrocker.platform.exception.ServiceRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static org.springframework.http.HttpMethod.OPTIONS;

@Component
public class RequestValidationInterceptor implements HandlerInterceptor {

    private AuthService authService;

    @Autowired
    public RequestValidationInterceptor(AuthService authService) {
        this.authService = authService;
    }

    private static final Logger log = LoggerFactory.getLogger(RequestValidationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("error")
                || request.getMethod().equals(OPTIONS.toString()))) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method methodName = handlerMethod.getMethod();
            log.info("Handler method : {} ", methodName.getName());
            String userToken = request.getHeader("token");
            validateUserToken(userToken);
            if(!authService.validateUserToken(userToken)) {
                log.error("Authentication fail for {} in user {} ", methodName, "user");
                throw new ServiceRuntimeException("Auth Error", "Authentication fail");
            }
        }
        return true;
    }

    private void validateUserToken(String userToken) {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
