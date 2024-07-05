package com.josue.client.domain.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class DocumentUniqueEntityException extends RepositoryException {

    private static final String DEFAULT_MESSAGE = "Document no valid: %s";
    private final String entity;
    private final Map<String, Object> params;

    public DocumentUniqueEntityException(String entity) {
        super(String.format(DEFAULT_MESSAGE, entity));
        this.params = Map.of();
        this.entity = entity;
    }

    public DocumentUniqueEntityException(String entity, Map<String, Object> params) {
        super(String.format(DEFAULT_MESSAGE, entity));
        this.params = params;
        this.entity = entity;
    }

    public DocumentUniqueEntityException(String message, String entity, Map<String, Object> params) {
        super(String.format(message, entity));
        this.params = params;
        this.entity = entity;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public Map<String, Object> getDetails() {
        return this.params;
    }

}
