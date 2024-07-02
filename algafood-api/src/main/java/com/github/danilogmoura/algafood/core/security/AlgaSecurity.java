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

        Long usuarioId = jwt.getClaim("usuario_id");

        if (usuarioId == null) {
            long randomLong = RANDOM.nextLong();
            usuarioId = randomLong < 0 ? randomLong : -randomLong;
        }

        return usuarioId;
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
}
