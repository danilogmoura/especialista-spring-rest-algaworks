package com.github.danilogmoura.algafood.client.model.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoInput {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeInput cidade;

}
