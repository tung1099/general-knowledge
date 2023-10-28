package com.example.common.config.exception;

import com.example.common.config.exception.exception_class.FileNotFoundException;
import com.example.common.config.exception.exception_class.ValidateException;
import com.example.common.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleValidateException(HttpServletRequest request, Exception ex) {
        log.error("handleValidateException {}\n", request.getRequestURI(), ex);
        log.info("Exception: ['{}']", (Object) ex.getStackTrace());
        return new ResponseError(ErrorCode.E400, ex.getMessage());
    }
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleFileNotFoundException(HttpServletRequest request, Exception ex) {
        log.error("handleFileNotFoundException{}\n", request.getRequestURI(), ex);
        log.info("Exception: ['{}']", (Object) ex.getStackTrace());
        return new ResponseError(ErrorCode.E404, ex.getMessage());
    }
}
