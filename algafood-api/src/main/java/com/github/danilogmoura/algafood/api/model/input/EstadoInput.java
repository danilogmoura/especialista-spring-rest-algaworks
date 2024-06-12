package com.github.danilogmoura.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

    @ApiModelProperty(example = "São Paulo", required = true)
    @NotBlank
    private String nome;
}
