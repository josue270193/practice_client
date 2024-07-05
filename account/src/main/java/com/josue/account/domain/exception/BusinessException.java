package com.josue.account.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public BusinessException(String message) {
        super(message);
    }

}
