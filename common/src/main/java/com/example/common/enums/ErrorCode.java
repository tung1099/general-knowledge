package com.example.common.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    E00("API call successful, no data response"),
    E400("Bad request"),
    E401("Unauthenticated"),
    E404("Not found"),
    E500("Internal server error");

    private String description;
    ErrorCode(String description) {
        this.description = description;
    }
}
