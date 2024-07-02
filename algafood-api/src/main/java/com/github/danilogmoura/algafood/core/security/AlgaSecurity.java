package com.github.danilogmoura.algafood.core.security;

import com.github.danilogmoura.algafood.domain.repository.PedidoRepository;
import com.github.danilogmoura.algafood.domain.repository.RestauranteRepository;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AlgaSecurity {

    private final static Random RANDOM = new Random();

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuarioId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("usuario_id");
    }

    public boolean gerenciaRestaurante(Long restauranteId) {
        if (restauranteId == null) {
            return false;
        }

        return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
    }


    public boolean gerenciaRestauranteDoPedido(String codigoPedido) {
        if (codigoPedido.isBlank()) {
            return false;
        }

        return pedidoRepository.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
    }

    public boolean usuarioAutenticadoIgual(Long usuarioId) {
        return getUsuarioId() != null && usuarioId != null &&
            getUsuarioId().equals(usuarioId);
    }
}
