package com.schedule_project.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorRespnse {
    private final int status;
    private final String error;
    private final String message;
    private final String path;


    public ErrorRespnse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
