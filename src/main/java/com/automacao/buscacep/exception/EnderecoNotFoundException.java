package com.automacao.buscacep.exception;

public class EnderecoNotFoundException extends RuntimeException{
    public EnderecoNotFoundException(String message) {
        super(message);
    }
}
