package com.github.danilogmoura.algafood.domain.exception;

public class EstadoNaoEcontradoException extends EntidadeNaoEcontradaException {

    public EstadoNaoEcontradoException(String message) {
        super(message);
    }

    public EstadoNaoEcontradoException(Long estadoId) {
        this(String.format("Não existe um cadastro de estado com código %d", estadoId));
    }
}
