package com.github.danilogmoura.algafood.domain.exception;

public abstract class EntidadeNaoEcontradaException extends NegocioException {

    public EntidadeNaoEcontradaException(String message) {
        super(message);
    }
}
