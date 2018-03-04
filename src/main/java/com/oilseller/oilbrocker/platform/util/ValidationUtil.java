package com.oilseller.oilbrocker.platform.util;

import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationUtil {

    private static final Logger log = LoggerFactory.getLogger(ValidationUtil.class);

    public static void validate(String string, String message) {

        if (string == null || string.isEmpty() ) {
            log.error("Validation fail. {}", message);
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, message);
        }
    }

    public static void validate(Object object, String message){

        if (object == null) {
            log.error("Validation fail. {}", message);
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, message);
        }
    }
}
