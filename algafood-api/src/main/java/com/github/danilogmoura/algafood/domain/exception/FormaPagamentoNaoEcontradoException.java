package com.github.danilogmoura.algafood.domain.exception;

public class FormaPagamentoNaoEcontradoException extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEcontradoException(String message) {
        super(message);
    }

    public FormaPagamentoNaoEcontradoException(Long formaPagamentoId) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", formaPagamentoId));
    }
}
