package com.github.danilogmoura.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "José Souza")
    private String nome;

    @ApiModelProperty(example = "jose@email.com")
    private String email;
}
