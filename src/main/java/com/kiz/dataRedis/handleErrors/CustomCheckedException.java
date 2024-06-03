package com.kiz.dataRedis.handleErrors;

public class CustomCheckedException extends Exception {
    public CustomCheckedException(String message, Exception e) {
        super(message, e);
    }
}
