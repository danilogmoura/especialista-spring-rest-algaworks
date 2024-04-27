package com.github.danilogmoura.algafood.domain.exception;

public class RestauranteNaoEcontradoException extends EntidadeNaoEcontradaException {

    public RestauranteNaoEcontradoException(String message) {
        super(message);
    }

    public RestauranteNaoEcontradoException(Long restauranteId) {
        this(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
    }
}
