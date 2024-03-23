package com.william.gestao_vagas.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException () {
        super("Job not found");
    }
}
