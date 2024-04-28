package com.automacao.buscacep.exception;

public class HttpRequestFailedException extends RuntimeException {
    public HttpRequestFailedException(String message) {
        super(message);
    }
}