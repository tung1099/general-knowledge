package com.example.common.config.exception;

import com.example.common.enums.ErrorCode;

public class ErrorCustomMessage {
    static String E_BAD_REQUEST = "Input chứa các trường giá trị lỗi hoặc các trường required đang null";
    static String E_NOT_FOUND = "Rất tiếc, không tìm thấy nội dung yêu cầu.";
    static String E_TIMEOUT = "Rất tiếc, hệ thống đang bận, vui lòng thử lại sau.";
    static String E_INTERNAL_SERVER = "Rất tiếc, hệ thống đang bận, vui lòng thử lại sau.";
    static String UNKNOWN = "Rất tiếc, hệ thống đang bận, vui lòng thử lại sau.";

    public static String getErrorMessage(ErrorCode errorCode){
        switch (errorCode){
            case E400:
                return E_BAD_REQUEST;
            case E404:
            case E00:
                return E_NOT_FOUND;
            case E500:
                return E_INTERNAL_SERVER;
            default:
                return UNKNOWN;
        }
    }
}
