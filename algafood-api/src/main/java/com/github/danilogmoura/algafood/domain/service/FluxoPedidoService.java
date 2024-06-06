package com.github.danilogmoura.algafood.domain.service;

import com.github.danilogmoura.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigoPedido) {
        var pedido = pedidoService.buscarOuFalhar(codigoPedido);

        var mensagem = Mensagem.builder()
            .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
            .corpo("pedido-confirmado.ftl")
            .variavel("pedido", pedido)
            .destinatario(pedido.getCliente().getEmail())
            .build();

        envioEmailService.enviar(mensagem);

        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        var pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        var pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.cancelar();
    }
}
