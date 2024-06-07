package com.github.danilogmoura.algafood.domain.listener;

import com.github.danilogmoura.algafood.domain.event.PedidoCanceladoEvent;
import com.github.danilogmoura.algafood.domain.service.EnvioEmailService;
import com.github.danilogmoura.algafood.domain.service.EnvioEmailService.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoCanceladoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        var pedido = event.getPedido();

        var mensagem = Mensagem.builder()
            .assunto(pedido.getRestaurante().getNome() + " - Pedido cancelado")
            .corpo("pedido-cancelado.ftl")
            .variavel("pedido", pedido)
            .destinatario(pedido.getCliente().getEmail())
            .build();

        envioEmailService.enviar(mensagem);
    }
}
