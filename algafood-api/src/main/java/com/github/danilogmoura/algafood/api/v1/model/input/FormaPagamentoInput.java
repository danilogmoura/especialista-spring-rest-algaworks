package com.github.danilogmoura.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

    @ApiModelProperty(example = "Pix", required = true)
    @NotBlank
    private String descricao;
}
