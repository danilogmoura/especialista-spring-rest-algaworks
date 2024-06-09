package com.github.danilogmoura.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

    @ApiModelProperty(example = "Urbelândia", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;
}
