package com.oilseller.oilbrocker.platform.interceptor;


import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import com.oilseller.oilbrocker.user.service.AuthService;
import com.oilseller.oilbrocker.user.service.TokenService;
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

    private static final Logger log = LoggerFactory.getLogger(RequestValidationInterceptor.class);
    private AuthService authService;
    private TokenService tokenService;

    @Autowired
    public RequestValidationInterceptor(TokenService tokenService, AuthService authService) {
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isaAuthenticateRequired(request)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method methodName = handlerMethod.getMethod();
            log.info("Handler method : {} ", methodName.getName());
            String accessToken = request.getHeader("Authorization");
            validateUserToken(accessToken);
            if (!authService.validateUserToken(accessToken)) {
                log.error("Authentication fail for {} in user {} ", methodName, "user");
                throw new ServiceRuntimeException(ErrorCode.AUTH_ERROR, "Authentication fail");
            }
        }
        return true;
    }

    private boolean isaAuthenticateRequired(HttpServletRequest request) {
        return !(request.getRequestURI().contains("login") || request.getRequestURI().contains("error")
                || request.getMethod().equals(OPTIONS.toString()) || request.getRequestURI().contains("order/place")
                || request.getRequestURI().contains("product/list") || request.getRequestURI().contains("productR/load")
        || request.getRequestURI().contains("order/load"));
    }

    private void validateUserToken(String userToken) {

        if (!tokenService.isValidRequest(userToken)) {
            throw new ServiceRuntimeException(ErrorCode.AUTH_ERROR, "Invalid Token");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
