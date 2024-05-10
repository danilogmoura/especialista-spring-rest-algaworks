package com.github.danilogmoura.algafood.domain.repository;

import com.github.danilogmoura.algafood.domain.model.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}
