package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.PedidoNaoEncontradoException;
import com.github.danilogmoura.algafood.domain.model.Pedido;
import com.github.danilogmoura.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarOuFalhar(String codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido)
            .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }
}
