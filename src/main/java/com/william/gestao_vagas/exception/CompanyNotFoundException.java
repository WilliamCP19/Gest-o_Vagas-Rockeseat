package com.william.gestao_vagas.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(){
        super("Company não encotrada");
    }
}
