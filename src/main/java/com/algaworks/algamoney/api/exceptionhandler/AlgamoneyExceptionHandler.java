package com.algaworks.algamoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String messageUser = messageSource.getMessage("invalid.message", null, Locale.US);
        String mensagemDevelopment = ex.getCause().toString();
        return handleExceptionInternal(ex, new Error(messageUser, mensagemDevelopment), headers, HttpStatus.BAD_REQUEST, request);
    }

    public static class Error {

        private String messageUser;
        private String mensagemDevelopment;

        public Error(String messageUser, String mensagemDevelopment) {
            this.messageUser = messageUser;
            this.mensagemDevelopment = mensagemDevelopment;
        }

        public String getMessageUser() {
            return messageUser;
        }

        public String getMensagemDevelopment() {
            return mensagemDevelopment;
        }
    }
}
