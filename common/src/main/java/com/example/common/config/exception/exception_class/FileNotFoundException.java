package com.example.common.config.exception.exception_class;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileNotFoundException extends RuntimeException{
    private Object data;

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Object data) {
        super(message);
        this.data = data;
    }
}
