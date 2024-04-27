package com.github.danilogmoura.algafood.domain.exception;

public class CidadeNaoEcontradaException extends EntidadeNaoEcontradaException {

    public CidadeNaoEcontradaException(String message) {
        super(message);
    }

    public CidadeNaoEcontradaException(Long cidadeId) {
        this(String.format("Não existe um cadastro de cidade com código %d", cidadeId));
    }
}
