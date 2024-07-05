package com.josue.client.infrastructure.configuration;

import com.josue.client.domain.exception.RepositoryException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    protected ProblemDetail handleHibernateRepositoryException(Exception ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
        problemDetail.setTitle(ex.getClass().getSimpleName());
        return problemDetail;
    }

    @ExceptionHandler({RepositoryException.class})
    protected ProblemDetail handleRepositoryException(RepositoryException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setTitle(ex.getClass().getSimpleName());
        problemDetail.setProperty("details", ex.getDetails());
        return problemDetail;
    }

}
