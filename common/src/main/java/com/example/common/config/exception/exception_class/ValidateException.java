package com.example.common.config.exception.exception_class;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidateException extends RuntimeException {
    private Object data;

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Object data) {
        super(message);
        this.data = data;
    }
}
