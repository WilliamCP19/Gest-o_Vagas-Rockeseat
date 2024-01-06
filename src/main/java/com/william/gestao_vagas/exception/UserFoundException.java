package com.william.gestao_vagas.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException () {
        super("Usuario já existe!");
    }
}
