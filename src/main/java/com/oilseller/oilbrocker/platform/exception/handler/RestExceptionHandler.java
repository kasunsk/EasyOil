package com.oilseller.oilbrocker.platform.exception.handler;

import com.oilseller.oilbrocker.platform.exception.dto.ApiError;
import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by kasun on 11/26/17.
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ServiceRuntimeException.class)
    protected ResponseEntity<Object> applicationException(ServiceRuntimeException ex) {

        ApiError apiError;
        String errorCode = ex.getErrorCode();

        switch (errorCode) {
            case ErrorCode.AUTH_ERROR:
                apiError = new ApiError(HttpStatus.UNAUTHORIZED);
                apiError.setMessage(ex.getMessage());
                break;
            case ErrorCode.EMPTY_REQUEST:
            case ErrorCode.INSUFFICIENT_DATA:
                apiError = new ApiError(HttpStatus.BAD_REQUEST);
                apiError.setMessage(ex.getMessage());
                break;
            case ErrorCode.NOT_FOUND:
                apiError = new ApiError(HttpStatus.NOT_FOUND);
                apiError.setMessage(ex.getMessage());
                break;
            case ErrorCode.ALREADY_EXIST:
                apiError = new ApiError(HttpStatus.ALREADY_REPORTED);
                apiError.setMessage(ex.getMessage());
                break;
            default:
                apiError = new ApiError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), ex);
                break;
        }
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
