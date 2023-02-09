package com.algaworks.algamoney.api.exceptionhandler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
        String developmentMessage = ex.getCause().toString();
        List<Error> errorList = Arrays.asList(new Error(userMessage, developmentMessage));
        return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errorList = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("resource.not.found", null, LocaleContextHolder.getLocale());
        String developmentMessage = ex.toString();
        List<Error> errorList = Arrays.asList(new Error(userMessage, developmentMessage));
        return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String userMessage = messageSource.getMessage("resource.operation-not-permited", null, LocaleContextHolder.getLocale());
        String developmentMessage = ExceptionUtils.getRootCauseMessage(ex);
        List<Error> errorList = Arrays.asList(new Error(userMessage, developmentMessage));
        return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> createErrorList(BindingResult bindingResult) {
        List<Error> errorList = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developmentMessage = fieldError.toString();
            errorList.add(new Error(userMessage, developmentMessage));
        }
        return errorList;
    }

    public static class Error {

        private String userMessage;
        private String developmentMessage;

        public Error(String userMessage, String developmentMessage) {
            this.userMessage = userMessage;
            this.developmentMessage = developmentMessage;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public String getDevelopmentMessage() {
            return developmentMessage;
        }
    }
}
