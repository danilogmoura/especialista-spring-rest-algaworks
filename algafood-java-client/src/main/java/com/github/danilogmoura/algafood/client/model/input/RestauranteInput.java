package com.github.danilogmoura.algafood.client.model.input;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestauranteInput {

    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaInput cozinha;
    private EnderecoInput endereco;

}
