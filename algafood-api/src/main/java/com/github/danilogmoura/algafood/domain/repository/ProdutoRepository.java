package com.github.danilogmoura.algafood.domain.repository;

import com.github.danilogmoura.algafood.domain.model.Produto;
import com.github.danilogmoura.algafood.domain.model.Restaurante;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long> {

    Set<Produto> findAllByRestaurante(Restaurante restaurante);

    @Query("from Produto p where p.restaurante.id = :restaurante and p.id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long restauranteId, @Param("produto") Long produtoId);
}
