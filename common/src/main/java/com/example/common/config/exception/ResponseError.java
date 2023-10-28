package com.example.common.config.exception;

import com.example.common.enums.ErrorCode;
import lombok.Data;

@Data
public class ResponseError {
    private String errorCode;
    private String description;
    private String error;

    public ResponseError() {
    }
    public ResponseError(String errorCode, String description, String error) {
        this.errorCode = errorCode;
        this.description = description;
        this.error = error;
    }
    public ResponseError(ErrorCode errorCode, String message) {
        this(errorCode.name(), errorCode.getDescription(), message);
    }

    public ResponseError(ErrorCode errorCode) {
        this();
        this.errorCode = errorCode.name();
        this.description = ErrorCustomMessage.getErrorMessage(errorCode);
        this.error = ErrorCustomMessage.getErrorMessage(errorCode);
    }
}
