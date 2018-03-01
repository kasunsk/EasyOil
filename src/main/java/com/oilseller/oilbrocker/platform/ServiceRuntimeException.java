package com.oilseller.oilbrocker.platform;

public class ServiceRuntimeException extends RuntimeException {


    private String errorMsg;
    private String errorCode;

    public String getErrorMsg() {
        return errorMsg;
    }

    public ServiceRuntimeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ServiceRuntimeException(String errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
