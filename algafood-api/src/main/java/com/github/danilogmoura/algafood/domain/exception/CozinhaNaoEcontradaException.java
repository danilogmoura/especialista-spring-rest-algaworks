package com.github.danilogmoura.algafood.domain.exception;

public class CozinhaNaoEcontradaException extends EntidadeNaoEcontradaException {

    public CozinhaNaoEcontradaException(String message) {
        super(message);
    }

    public CozinhaNaoEcontradaException(Long cozinhaId) {
        this(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
    }
}
