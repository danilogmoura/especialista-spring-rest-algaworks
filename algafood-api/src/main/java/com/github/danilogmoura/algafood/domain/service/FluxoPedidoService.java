package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.exception.NegocioException;
import com.github.danilogmoura.algafood.domain.model.Pedido;
import com.github.danilogmoura.algafood.domain.model.Pedido.StatusPedido;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    private static final String MSG_STATUS_INVALIDO = "Status do pedido '%s' não pode ser alterado de '%s' para '%s'";

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        var pedido = pedidoService.buscarOuFalhar(pedidoId);
        validarStatus(pedido, StatusPedido.CRIADO, StatusPedido.CONFIRMADO);
    }

    @Transactional
    public void entregar(Long pedidoId) {
        var pedido = pedidoService.buscarOuFalhar(pedidoId);
        validarStatus(pedido, StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE);
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        var pedido = pedidoService.buscarOuFalhar(pedidoId);
        validarStatus(pedido, StatusPedido.CRIADO, StatusPedido.CANCELADO);
    }

    private static void validarStatus(Pedido pedido, StatusPedido statusAtual, StatusPedido statusNovo) {
        if (!pedido.getStatus().equals(statusAtual)) {
            throw new NegocioException(
                String.format(MSG_STATUS_INVALIDO, pedido.getId(), pedido.getStatus().getDescricao(), statusNovo.getDescricao()));
        }

        pedido.setStatus(statusNovo);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }
}
