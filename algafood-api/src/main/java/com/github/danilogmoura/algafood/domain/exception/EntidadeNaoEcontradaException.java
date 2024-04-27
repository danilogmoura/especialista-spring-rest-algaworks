package com.github.danilogmoura.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class EntidadeNaoEcontradaException extends NegocioException {

    public EntidadeNaoEcontradaException(String message) {
        super(message);
    }
}
