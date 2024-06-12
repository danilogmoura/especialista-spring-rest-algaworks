package com.github.danilogmoura.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

    @ApiModelProperty(example = "38400-000", required = true)
    @NotBlank
    private String cep;

    @ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
    @NotBlank
    private String logradouro;

    @ApiModelProperty(example = "500", required = true)
    @NotBlank
    private String numero;

    @ApiModelProperty(example = "Apto 801")
    private String complemento;

    @ApiModelProperty(example = "Cambuí", required = true)
    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;
}
