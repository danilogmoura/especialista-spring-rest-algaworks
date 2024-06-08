package com.github.danilogmoura.algafood.client.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinhaModel;
    private boolean ativo;
    private boolean aberto;
    private EnderecoModel endereco;

}
