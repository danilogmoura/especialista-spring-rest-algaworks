package com.github.danilogmoura.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @ApiModelProperty(example = "38400-200")
    private String cep;

    @ApiModelProperty(example = "Rua Serafim Louveira")
    private String logradouro;

    @ApiModelProperty(example = "930")
    private String numero;

    @ApiModelProperty(example = "Casa 20")
    private String complemento;

    @ApiModelProperty(example = "Sumaré")
    private String bairro;

    private CidadeResumoModel cidade;
}
