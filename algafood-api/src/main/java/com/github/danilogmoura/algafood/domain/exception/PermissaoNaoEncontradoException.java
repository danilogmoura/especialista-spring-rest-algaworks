package com.github.danilogmoura.algafood.domain.exception;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PermissaoNaoEncontradoException(String message) {
        super(message);
    }

    public PermissaoNaoEncontradoException(Long permissaoId) {
        this(String.format("Não existe um cadastro de permissão com código %d", permissaoId));
    }
}
