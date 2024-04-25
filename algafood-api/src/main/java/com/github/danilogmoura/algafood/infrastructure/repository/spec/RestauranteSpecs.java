package com.github.danilogmoura.algafood.infrastructure.repository.spec;

import com.github.danilogmoura.algafood.domain.model.Restaurante;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecs {

    private RestauranteSpecs() {
    }

    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}
